// See LICENSE.txt for license details.
package examples

import java.io.File
import java.io.ByteArrayInputStream
import javax.sound.sampled._

object WavIn {
  def apply(filename: String) = {
    println("filename " + filename)
    val file = new File(filename)
    val aff = AudioSystem.getAudioFileFormat(file)
    println("AFF " + aff + " TYPE " + aff.getType())
    val ais = AudioSystem.getAudioInputStream(file)
    if (ais.getFormat.getChannels != 1 || ais.getFormat.getSampleSizeInBits != 8) {
      println(filename + " must be 8-bit monoaural")
      System.exit(-1)
    }
    new WavIn(ais)
  }
}
class WavIn(val stream: AudioInputStream) {
  def read      = stream.read
  def close     = stream.close
  def getFormat = stream.getFormat
}

object WavOut {
  def apply(filename: String, f: AudioFormat) = {
    new WavOut(filename, f)
  }
}
class WavOut(val filename: String, f: AudioFormat) extends AudioInputStream(new ByteArrayInputStream(Array[Byte]()), f, AudioSystem.NOT_SPECIFIED) {
  val buf = collection.mutable.ArrayBuffer[Byte]()
  var pos = 0
  def += (s: Byte) = buf += s
  override def available: Int = buf.length - pos
  override def read(out: Array[Byte], offs: Int, len: Int): Int = {
    val bytes = math.min(available, len)
    for (i <- 0 until bytes)
      out(offs + i) = buf(pos + i)
    pos += bytes
    bytes
  }
  def flush = {
    AudioSystem.write(this, AudioFileFormat.Type.WAVE, new File(filename))
  }
}

