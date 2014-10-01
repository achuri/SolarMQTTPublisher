import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.Calendar;
import java.util.Random;
import java.text.SimpleDateFormat;

public class GenerateMessage{
    public static void main(String[] argv) throws Exception {
        System.out.println(Generate(4));
    }
    public static String Generate(Integer iMsgCount) {
        String line = "";
        JSONArray arrMessage = new JSONArray();
        JSONObject obj = new JSONObject();
        String[] measurement = {"Output","Voltage","DailyOutput","RawOutput","Current"};
        //Integer threadSleepTimeInMilliSec = 1000;
        try {

            Integer i = 0;
            while(i <= iMsgCount){
                obj = new JSONObject();
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat format2 = new SimpleDateFormat("hh:mm:ss");
                Random rand = new Random();
                obj.put("inverterid", rand.nextInt(100));
                Integer var = rand.nextInt(5);
                Double dVal = 0.00;
                switch (var){
                    case 0:
                        dVal = (rand.nextInt(399999) + 4.00)*0.1;
                        break;
                    case 1:
                        dVal = rand.nextInt(299) + 1.00;
                        break;
                    case 2:
                        dVal = (rand.nextInt(360) + 1.1)*0.01;
                        break;
                    case 3:
                        dVal = rand.nextInt(39900) + 3.00;
                        break;
                    case 4:
                        dVal = (rand.nextInt(7) + 0.1) * 0.1;
                        break;
                    default: dVal = 1.00;
                }
                obj.put("measurement", measurement[var]);
                obj.put("value", dVal);
                obj.put("measureddate", format1.format(calendar.getTime()));
                obj.put("measuredtimestamp", format2.format(calendar.getTime()));

                arrMessage.add(obj);

                i++;
                //Thread.sleep(threadSleepTimeInMilliSec);
            }

        }catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

        return arrMessage.toJSONString();
    }
}
