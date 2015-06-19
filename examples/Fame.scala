package TutorialExamples

import Chisel._

class MemReq extends Bundle {
  val rw = Bool()
  val addr = UInt(width = 4)
  val writeData = UInt(width = 16)
}

class MemResp extends Bundle {
  val readData = UInt(width = 16)
}

//simple fsm that writes to 2 memory locations, reads back from the 2 memory locations, and compares the read data to the original write data
//this is a direct RTL aka FAME0 design
class Fame0Requester extends Module {
  val io = new Bundle {
    val req = new DecoupledIO(new MemReq)
    val resp = new DecoupledIO(new MemResp).flip
    val passed = Bool(OUTPUT)
    val failed = Bool(OUTPUT)
  }
  val writeReq0 :: writeReq1 :: readReq0 :: readReq1 :: readResp0 :: readResp1 :: passed :: failed :: Nil = Enum(UInt(), 8)
  val currentState = Reg(init = writeReq0)
  io.req.valid := Bool(false)
  io.req.bits.rw := Bool(false)
  io.req.bits.addr := UInt(0)
  io.req.bits.writeData := UInt(0)
  io.resp.ready := Bool(false)
  io.passed := Bool(false)
  io.failed := Bool(false)
  when(currentState === writeReq0){
    io.req.valid := Bool(true)
    io.req.bits.rw := Bool(true)
    io.req.bits.addr := UInt(0)
    io.req.bits.writeData := UInt(5)
    when(io.req.ready){
      currentState := writeReq1
    } 
  }.elsewhen(currentState === writeReq1){
    io.req.valid := Bool(true)
    io.req.bits.rw := Bool(true)
    io.req.bits.addr := UInt(1)
    io.req.bits.writeData := UInt(6)
    when(io.req.ready){
      currentState := readReq0
    } 
  }.elsewhen(currentState === readReq0){
    io.req.valid := Bool(true)
    io.req.bits.rw := Bool(false)
    io.req.bits.addr := UInt(0)
    when(io.req.ready){
      currentState := readReq1
    } 
  }.elsewhen(currentState === readReq1){
    io.req.valid := Bool(true)
    io.req.bits.rw := Bool(false)
    io.req.bits.addr := UInt(1)
    when(io.req.ready){
      currentState := readResp0
    } 
  }.elsewhen(currentState === readResp0){
    io.resp.ready := Bool(true)
    when(io.resp.valid){
      when(io.resp.bits.readData === UInt(5)){
        currentState := readResp1
      }.otherwise{
        currentState := failed
      }
    }
  }.elsewhen(currentState === readResp1){
    io.resp.ready := Bool(true)
    when(io.resp.valid){
      when(io.resp.bits.readData === UInt(6)){
        currentState := passed
      }.otherwise{
        currentState := failed
      }
    }
  }.elsewhen(currentState === passed){
    io.passed := Bool(true)
  }.elsewhen(currentState === failed){
    io.failed := Bool(true)
  }
}

//simple abstract FAME3 module that emulates a 1 cycle read latency memory unit by manipulating the host queues
class Fame3Responder extends Module {
  val io = new Bundle {
    val req = new FameDecoupledIO(new MemReq).flip
    val resp = new FameDecoupledIO(new MemResp)
  }
  val fireTgtClk = Wire(Bool())
  //target machine
  val mem = Mem(UInt(width = 16), 16)
  val receiveReq :: sendResp :: Nil = Enum(UInt(), 2)
  val targetState = Reg(init = receiveReq)
  val nextTargetState = Wire(UInt())
  when(fireTgtClk){
    targetState := nextTargetState
  }
  
  val readData = Reg(UInt(width = 16))
  
  nextTargetState := targetState
  io.req.target.ready := Bool(false)
  io.resp.target.valid := Bool(false)
  io.resp.target.bits.readData := UInt(0)
  when(targetState === receiveReq){
    io.req.target.ready := Bool(true)
    when(io.req.target.valid){
      when(io.req.target.bits.rw){
        when(fireTgtClk){
          mem.write(io.req.target.bits.addr, io.req.target.bits.writeData)
        }
      }.otherwise{
        when(fireTgtClk){
          readData := mem.read(io.req.target.bits.addr)
        }
        nextTargetState := sendResp
      }
    }
  }.elsewhen(targetState === sendResp){
    io.resp.target.valid := Bool(true)
    io.resp.target.bits.readData := readData
    when(io.resp.target.ready){
      nextTargetState := receiveReq
    }
  }
  
  //host machine 
  val host_idle :: host_add_read_delay :: host_send_read_data :: Nil = Enum(UInt(), 3)
  val hostState = Reg(init = host_idle)
  
  fireTgtClk := Bool(false)
  io.req.host_ready := fireTgtClk
  io.resp.host_valid := fireTgtClk
  when(hostState === host_idle){
    fireTgtClk := io.req.host_valid && io.resp.host_ready
    when(targetState === sendResp){
      fireTgtClk := Bool(false)
      hostState := host_add_read_delay
    }
  }.elsewhen(hostState === host_add_read_delay){//add extra cycle of read latency by inserting a empty token in the host resp queue
    fireTgtClk := Bool(false)
    when(io.req.host_valid && io.resp.host_ready){
      io.req.host_ready := Bool(true)
      io.resp.host_valid := Bool(true)
      io.resp.target.valid := Bool(false)
      hostState := host_send_read_data
    }
  }.elsewhen(hostState === host_send_read_data){//send actual read response
    fireTgtClk := io.req.host_valid && io.resp.host_ready
    when(fireTgtClk && io.resp.target.ready){
      hostState := host_idle
    }
  }
}

//Top level module that automatically transforms the FAME0 requestor to FAME1 and connects it to the FAME3 Responder
class FameTransform() extends Module {
  val io = new Bundle {
    val passed = Bool(OUTPUT)
    val failed = Bool(OUTPUT)
  }
  //instantiate FAME0 requester and FAME3 responder
  val requester = Module(new Fame1Wrapper(new Fame0Requester()))//apply fame transformation to FAME0 module
  val responder = Module(new Fame3Responder())

  //instantiate FameQueues
  val reqQueue = Module(new FameQueue(8)(new MemReq))
  val respQueue = Module(new FameQueue(8)(new MemResp))
 
  //connect transformed FAME0 requester to FAME3 responder through FameQueues
  FameDecoupledIO.connect(requester.DecoupledIOs("req"), reqQueue.io.enq, new MemReq)
  reqQueue.io.deq <> responder.io.req
  FameDecoupledIO.connect(requester.DecoupledIOs("resp"), respQueue.io.deq, new MemResp) 
  respQueue.io.enq <> responder.io.resp
  
  //wire debug signals from transformed FAME0 module to the outside
  io.passed := requester.DebugIOs("passed")
  io.failed := requester.DebugIOs("failed")
  
}

class FameTests(c: FameTransform) extends Tester(c) {
  var done = false
  while(!done){
    expect(c.io.failed, 0)
    if(peek(c.io.passed) == 1){
      done = true
    }
    step(1)
  }
}

