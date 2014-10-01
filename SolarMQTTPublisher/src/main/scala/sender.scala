import org.eclipse.paho.client.mqttv3.{MqttClient, MqttClientPersistence, MqttException, MqttMessage, MqttTopic,MqttConnectOptions}
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence

/**
 * A simple Mqtt publisher for demonstration purposes, repeatedly publishes
 * Space separated String Message "hello mqtt demo for spark streaming"
 */
object MQTTPublisher {

  var client: MqttClient = _

  def main(args: Array[String]) {
    if (args.length < 2) {
      System.err.println("Usage: MQTTPublisher <MqttBrokerUrl> <topic>")
      System.exit(1)
    }


    val Seq(brokerUrl, topic) = args.toSeq
    var options:MqttConnectOptions = new MqttConnectOptions()


    println("Trying to connect ")

    try {
      var peristance:MqttClientPersistence =new MqttDefaultFilePersistence("/tmp")
      client = new MqttClient(brokerUrl, MqttClient.generateClientId(), peristance)
	  
	  options.setUserName("admin")
    val pswd = "solarguard"
	  options.setPassword(pswd.toCharArray)
      println("connecting")
//       println(GenerateMessage.Generate(4))

    } catch {
      case e: MqttException => println("Exception Caught: " + e)
    }

    client.connect(options)

    val msgtopic: MqttTopic = client.getTopic(topic)
    val msg: String = "hello mqtt demo for spark streaming"
    
     while (true) {
    // val msg: String = GenerateMessage.Generate(4)
      val message: MqttMessage = new MqttMessage(String.valueOf(msg).getBytes("utf-8"))
      msgtopic.publish(message)
      println("Published data. topic: " + msgtopic.getName() + " Message: " + message)
      //Thread sleep 5000
    }
   client.disconnect()
  }
}
