import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        // input csv table
        Scanner scan = new Scanner(System.in);
        String rawHead = scan.nextLine();               // raw head of the table
        ArrayList<String> rawData = new ArrayList<>();  // raw data rows in the table
        while(scan.hasNextLine()){
            String cur = scan.nextLine();
            if (cur.isEmpty()){
                break;
            }
            else{
                rawData.add(cur);
            }
        }
        scan.close();

        // output the result
        System.out.println("country,year,cases");
        List<String> output = new Solution().process(rawHead, rawData);
        for (int i = 0; i < output.size();i++){
            System.out.println(output.get(i));
        }

    }


    private List<String> process(String head, List<String> data){
        String[] years = new String[0];
        try{
            years = head.split(",");   // country,1999,2000
            // check the nums of cols in head
            if (years.length == 0){
                throw new Exception("No head for the input csv file.");
            }
            else if (years.length == 1){
                throw new Exception("No years data for the input csv file.");
            }
            // check if the years are all digits
            for (int i = 1; i < years.length;i++){
                if (!years[i].matches("[0-9]+")){
                    throw new Exception("The year "+years[i]+" is invalid.");
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        // use a 2d array for data and check the format
        String[][] dataList = new String[data.size()][years.length];
        try{
            for (int i = 0; i < data.size(); i++){
                String[] row = data.get(i).split(",");
                if (row.length < years.length){
                    throw new Exception("Lack the data in row "+(i+1));
                }
                else if (row.length > years.length){
                    throw new Exception("The data in row "+(i+1)+" is redundant.");
                }
                else{
                    for (int j = 0; j < years.length; j++){
                        dataList[i][j] = row[j];
                    }
                }

            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // output list
        ArrayList<String> outputData = new ArrayList<>();
        for (int i = 1; i < years.length; i++){
            for (int j = 0; j < data.size();j++){
                StringBuffer buffer = new StringBuffer();
                buffer.append(dataList[j][0]);
                buffer.append(",");
                buffer.append(years[i]);
                buffer.append(",");
                buffer.append(dataList[j][i]);
                outputData.add(buffer.toString());
            }
        }
        return outputData;
    }


}
