package se.kth.iv1350.amazingpos.veiw;

import se.kth.iv1350.amazingpos.controller.*;
import se.kth.iv1350.amazingpos.model.Amount;

/**
 * This program has no "actual" view, instead, this class is a
 * placeholder for the entire view to run fake execution.
 */
public class View {

    private Controller contr;

    /**
     *  A new instance is created, that represent a view and uses the
     *  specified controller for all calls to other layers.
     *
     * @param @param contr The controller that is used for all calls
     * to other layers to perform system operations.
     */
    public View(Controller contr) {
        this.contr = contr;
    }

    /**
     * Creates a simple execution that can take inputs from
     * user for calling and running all system operations
     */
    public void sampleExecution () {
        System.out.println("New sale is started.\n");
        contr.startSale();
        String saleIfo = contr.scanItem("apple", 4 );
        System.out.println(saleIfo);
        saleIfo =  contr.scanItem("milk", 1 );
        System.out.println(saleIfo);
        saleIfo =  contr.scanItem("juice", 2 );
        System.out.println(saleIfo);
        saleIfo = contr.scanItem("banana", 3 );
        System.out.println(saleIfo);
        saleIfo =  contr.scanItem("apple", 2 );
        System.out.println(saleIfo);
        saleIfo =  contr.scanItem("milk", 2 );
        System.out.println(saleIfo);
        contr.showTotalPriceAndVAT();
        System.out.println(contr.showTotalPriceAndVAT());
        contr.discount("11111");
        Amount paidAmount = new Amount(100);
        contr.pay(paidAmount);
    }
}