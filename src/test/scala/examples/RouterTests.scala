// See LICENSE for license details.
package examples

import chisel3._
import chisel3.iotesters.{ChiselFlatSpec, OrderedDecoupledHWIOTester}

class RouterUnitTester(number_of_packets_to_send: Int) extends OrderedDecoupledHWIOTester {
  val device_under_test = Module(new Router)
  val c: Router = device_under_test
  enable_all_debug = true

  rnd.setSeed(0)

  def readRoutingTable(addr: Int, data: Int): Unit = {
    inputEvent(c.io.read_routing_table_request.bits.addr -> addr)
    outputEvent(c.io.read_routing_table_response.bits -> data)
  }

  def writeRoutingTable(addr: Int, data: Int): Unit = {
    inputEvent(
      c.io.load_routing_table_request.bits.addr -> addr,
      c.io.load_routing_table_request.bits.data -> data
    )
  }

  def writeRoutingTableWithConfirm(addr: Int, data: Int): Unit = {
    writeRoutingTable(addr, data)
    readRoutingTable(addr, data)
  }

  def routePacket(header: Int, body: Int, routed_to: Int): Unit = {
    inputEvent(c.io.in.bits.header -> header, c.io.in.bits.body -> body)
    outputEvent(c.io.outs(routed_to).bits.body -> body)
    logScalaDebug(s"rout_packet $header $body should go to out($routed_to)")
  }

  readRoutingTable(0, 0) // confirm we initialized the routing table

  // load routing table, confirm each write as built
  for (i <- 0 until Router.numberOfOutputs) {
    writeRoutingTableWithConfirm(i, (i + 1) % Router.numberOfOutputs)
  }
  // check them in reverse order just for fun
  for (i <- Router.numberOfOutputs - 1 to 0 by -1) {
    readRoutingTable(i, (i + 1) % Router.numberOfOutputs)
  }

  // send some regular packets
  for (i <- 0 until Router.numberOfOutputs) {
    routePacket(i, i * 3, (i + 1) % 4)
  }

  // generate a new routing table
  private val new_routing_table = Array.tabulate(Router.routeTableSize) { _ =>
    scala.util.Random.nextInt(Router.numberOfOutputs)
  }

  // load a new routing table
  for ((destination, index) <- new_routing_table.zipWithIndex) {
    writeRoutingTable(index, destination)
  }

  // send a bunch of packets, with random values
  for (i <- 0 until number_of_packets_to_send) {
    val data = rnd.nextInt(Int.MaxValue - 1)
    routePacket(i % Router.routeTableSize, data, new_routing_table(i % Router.routeTableSize))
  }
}

class RouterTests extends ChiselFlatSpec {
  val number_of_packets = 20
  "a router" should "have its routing table loaded and changed and route a bunch of packets" in {
    assertTesterPasses {
      new RouterUnitTester(number_of_packets)
    }
  }
}
