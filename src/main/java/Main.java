import model.InputData;
import service.PrintingService;
import service.PrintingServiceImpl;

import java.math.BigDecimal;

public class Main {

    private PrintingService printingService;
    public static void main(String[] args) {
        InputData inputData=new InputData()
                .withAmount(new BigDecimal("298000"))
                .withMonthDuration(BigDecimal.valueOf(160));

        PrintingService printingService=new PrintingServiceImpl();
        printingService.printInputDataInfo(inputData);
    }
}
