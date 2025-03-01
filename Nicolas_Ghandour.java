/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package nicolas_ghandour;

import java.util.Scanner ;

/**
 * Tester to test the Pharmacy, Medicine, Prescription and OverTheCounter 
 * classes. 
 * @version 1.1 April 2023
 * @author Nicolas Ghandour
 */
public class Nicolas_Ghandour {
    Scanner sc = new Scanner(System.in) ;
    
    /**
    *the main method that will create a new pharmacy object. The menu will 
    *be then displayed in a loop until the user chooses number 8 and the will 
    *execute the task chosen by the user from the menu. 
    */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to \" myPharma \" pharmacy system.");
        Pharmacy pharmacy = new Pharmacy("myPharma") ;

        int choice; /*represents the choice of the user  */
        
        /**
         * this variable is used to facilitate displaying the 
         * corresponding messages (when the user's input is invalid) in the each  
         * do while of case of the switch body
         */
        boolean firstIteration;  
     
        do{
            
            /*Displaying the menu and promping the user to enter a choice*/
            choice = menu(); 
            System.out.println();
            
            /*executing the taskes*/
            switch(choice){
                case 1:           /*Adding a medicine*/
                    char choiceTypeOfMedicine;
                    String name;
                    String composition;
                    int dose;
                    int quantity;
                    double price;
                    
                    /*inputing the type of the medicine*/
                    firstIteration = true;
                    System.out.println("Enter the information of the new"
                                       + " medicine: \n");
                    do {
                        System.out.println((firstIteration) 
                                ? "Choose O or o for over the counter medicine "
                                 + "and p or P for \nprescription medicine: "
                                : "Your choice is invalid. Please choose again:" 
                                 + " (O or o for \nover the counter medicine"
                                 + " and p or P for prescription \nmedicine) ");
                        System.out.print("(NOTICE: in case you entered a word"
                                         + " by mistake, the system\nwill "
                                         + "consider only the first character."
                                         + ") ");
                        choiceTypeOfMedicine = 
                             Character.toUpperCase(sc.next().charAt(0));
                        firstIteration = false;
                    } while ((choiceTypeOfMedicine != 'O') 
                            && (choiceTypeOfMedicine!= 'P'));
                    
                    /*inputing the name of the medicine*/
                    System.out.print("name: ");
                    name = sc.next().toLowerCase();
                    
                    /*inputing the composition of the medicine*/
                    System.out.print("composition: ");
                    composition = sc.next().toLowerCase();
                    
                    /*inputing the dose of the medicine*/
                    firstIteration = true;
                    do {
                        System.out.print( (firstIteration)
                                          ?"dose: "
                                          :"Dose must be a positive number."
                                             + " Please enter dose again: " );
                        dose = sc.nextInt();
                        firstIteration = false;
                    } while (dose <= 0);
                    
                    /*inputing the quantity of the medicine*/
                    firstIteration = true;
                    do {
                        System.out.print(( firstIteration)
                                ?"quantity: "
                                :"Quantity must be a positive number. Please "
                                  + "enter quantity again: ");
                        quantity = sc.nextInt();
                        firstIteration = false;
                    } while (quantity <= 0);
                    
                    /* inputing the price of the medicine*/
                    firstIteration = true;
                    do {
                        System.out.print(( firstIteration)
                                ? "price: "
                                : "Price must be a positive number. Please "
                                 + "enter price again: ");
                        price = sc.nextDouble();
                        firstIteration = false;
                    } while (price <= 0);
                    
                    /* checking the type of the medicine in order to know which 
                       information of the medicine we need to ask the user to 
                       enter. */ 
                    if (choiceTypeOfMedicine == 'P'){
                        System.out.print("Enter the doctor specialization "
                        + "for this medicine: ");
                        String specialization = sc.next().toLowerCase();
                        
                        /* calling the addMedicine method to add the medicine.*/
                        pharmacy.addMedicine(new Prescription (name, 
                                                               composition,
                                                               dose, price, 
                                                                    quantity,
                                              specialization));
                    } else { 
                        System.out.print("Enter the minimum Age "
                                         + "for this medicine: ");
                        int minAge = sc.nextInt();
                        
                        /* calling the addMedicine method to add the medicine.*/
                        pharmacy.addMedicine(new OverTheCounter(name, 
                                                                composition,
                                                                dose, price, 
                                                            quantity, minAge));
                    }
                    break;   
                    
                case 2: /* Search for a medicine by name */
                    int numberOfResultsN;/*this variable ends with C to 
                            differentiate between the numberOfResults in case 2 
                            and that of the case 3 in the switch*/
                    String nameToSearch;
                    int [] idexesOfMatchingName;
                    
                    /*inputing the name to search*/
                    System.out.println("Enter the name of the medicine to be "
                                    + "found: "); 
                    nameToSearch = (sc.next()).toLowerCase();
                    
                    idexesOfMatchingName = pharmacy.searchByName
                                                            (nameToSearch);
                    numberOfResultsN = idexesOfMatchingName.length;
                    
                    if (numberOfResultsN == 0) {
                        System.out.println("no medicine found having "
                                          + "this name.");
                    } else {
                        Medicine temporary;/* used to refere to each medicine
                                            in the array */
                        String tempType; /* temporary string indicating the  
                                         type of each medicine*/
                        
                        System.out.println(numberOfResultsN 
                                           + ( (numberOfResultsN == 1)
                                              ? " medicine" 
                                              : " medicines")
                                           + " found matching this name. " );
                        for (int count = 0; count<numberOfResultsN ; count++){
                            temporary = pharmacy.getMedicines()
                                       [idexesOfMatchingName[count]];
                            
                            /* casting the medicine to its type in 
                             to know which message to display */
                            temporary = (temporary instanceof Prescription)
                                       ? (Prescription)temporary
                                       : (OverTheCounter)temporary; 
                            tempType = (temporary instanceof Prescription)
                                        ? "Prescription : "
                                        : "Over the Counter : " ;
                            
                            /*displaying the medicine infos*/
                            System.out.println((count + 1) + " - " + tempType
                                               + "\n" + temporary); 
                        }
                    }
                    break ;
                    
                case 3:      /* Search for a medicine by composition */
                    String compositionToSearch;
                    int numberOfResultsC;/*this variable ends with C to 
                            differentiate between the numberOfResults in case 2 
                            and that of the case 3 in the switch*/
                    int [] idexesOfMatchingComposition;
                    
                    /* inputing the composition to search */
                    System.out.print("Enter the composition of the medicine to"
                                     + " be found: ");
                    compositionToSearch = (sc.next()).toLowerCase();
                    idexesOfMatchingComposition = pharmacy.searchByComposition 
                                                (compositionToSearch);
                    numberOfResultsC = idexesOfMatchingComposition.length;
                    
                    if (numberOfResultsC == 0) {
                        System.out.println("no medicine found having this "
                                          + "composition.");
                    } else {
                        Medicine temporary;/* used to refere to each medicine in 
                                            the array */
                        String tempType;/*temporary string indicating the type  
                                          of each medicine */
                        
                        System.out.println( numberOfResultsC +
                                          ((numberOfResultsC == 1)?" medicine"
                                                  :" medicines") 
                                           + " found matching this name. " );
                        
                        for (int count = 0; count<numberOfResultsC ; count++){
                            temporary = pharmacy.getMedicines()
                                        [idexesOfMatchingComposition[count]];
                             
                            /* casting the medicine to its type in  to know 
                            which message to display */
                            temporary = (temporary instanceof Prescription)
                                       ? (Prescription)temporary
                                       : (OverTheCounter)temporary; 
                            tempType = (temporary instanceof Prescription)
                                        ?" Prescription :"
                                        :" Over the Counter :" ;
                            
                            /*printing the medicines' informations */
                            System.out.println((count + 1) + " - " + tempType
                                              + "\n" + temporary );
                        }               
                    }
                    break ;
                    
                case 4:                 /*Sell a medicine*/
                    String nameOfTheMedicineToSell;
                    int doseOfTheMedicineToSell ;
                    int quantityOfTheMedicineToSell;

                    /* inputing the name */ 
                    System.out.print("Enter the name of the medicine: ");
                    nameOfTheMedicineToSell = sc.next().toLowerCase();
                    
                    /* inputing the dose */
                    firstIteration = true;
                    do {
                    System.out.print((firstIteration)?"Enter the dose: "
                                                     :"Dose must be a positive "
                                                      + "number. Please enter "
                                                      + "dose again: ");
                    doseOfTheMedicineToSell = sc.nextInt();
                    firstIteration = false;
                    } while(doseOfTheMedicineToSell <=0 );    
                    
                    /* inputing the quantity */       
                    firstIteration = true;
                    do {
                    System.out.print((firstIteration)? "Enter the quantity: "
                                                     : "Quantity must be a "
                                                      + "positive number"
                                                      + " Please enter quantity"
                                                      + " again: ");
                    quantityOfTheMedicineToSell = sc.nextInt();
                    firstIteration = false;
                    } while (quantityOfTheMedicineToSell <= 0);

                    pharmacy.sellMedicine(nameOfTheMedicineToSell,
                                          doseOfTheMedicineToSell, 
                                       quantityOfTheMedicineToSell);
                    break;
                    
                case 5:             /* Restock a medicine */
                    String nameOfTheMedicineToRestock;
                    int doseOfTheMedicineToRestock;
                    int quantityOfTheMedicineToRestock;
                    
                    /* inputing the name */
                    System.out.print("Enter the name of the medicine: ");
                    nameOfTheMedicineToRestock = sc.next().toLowerCase();
                    
                    /* inputing the dose */
                    firstIteration = true;
                    do {
                        System.out.print(( firstIteration)
                                          ?"dose: "
                                          :"Dose must be a positive number. "
                                            + "Please enter dose again: ");
                        doseOfTheMedicineToRestock = sc.nextInt();
                        firstIteration = false;
                    } while (doseOfTheMedicineToRestock <= 0);           
                    
                    /* inputing the quantity */
                    firstIteration = true;
                    do {
                        System.out.print((firstIteration)
                                         ?"quantity: "
                                         :"Quantity must be a positive number."
                                          + " Please enter quantity again:");
                        quantityOfTheMedicineToRestock = sc.nextInt();
                        firstIteration = false;
                    } while (quantityOfTheMedicineToRestock <= 0);  
                    
                    /*calling the restock method in the pharmacy class */
                    pharmacy.restock(nameOfTheMedicineToRestock,
                                     doseOfTheMedicineToRestock, 
                                  quantityOfTheMedicineToRestock);
                    break;    
                    
                case 6:       /* Display all medicines */
                    if (pharmacy.getNumberOfMedicines()==0) {
                        System.out.println("The pharmacy has no medicines");
                    } else {
                        Medicine temporary; // used to refere to each medicine 
                                            // in the array 
                        String TypeOfTempMedicine;// temporary string indicating  
                                                  //the type of each medicine

                        for (int count=0; count<pharmacy.getNumberOfMedicines()
                             ; count++){
                                        temporary =  pharmacy.getMedicines()
                                                                        [count];

                                        /* casting the medicine to its type in 
                                        to know which message to display */
                                        temporary = (temporary instanceof 
                                                                   Prescription)
                                                  ? (Prescription)temporary
                                                  : (OverTheCounter)temporary; 

                                        TypeOfTempMedicine = 
                                                        (temporary instanceof 
                                                                 Prescription)
                                                        ? "Prescription : " 
                                                        : "Over the Counter : "; 
                                                                              
                                        /*printing the informations*/
                                        System.out.println("Medicine " 
                                                + (count + 1) + " : "
                                                + TypeOfTempMedicine+ "\n" 
                                                + temporary + "\n----");           
                        }
                    }        
                    break;
                    
                case 7:    /* Display information */
                    System.out.print( pharmacy);
                    break;
                    
                case 8:  /* Exit */
                    System.exit(0);
            }
          System.out.println();
        } while (true); 
      
    }
         
   /*
    * method  that will display the following menu and return 
    * the choice of the user:
    */
    public static int menu(){
        Scanner sc = new Scanner(System.in);

        int choice;//represents the choice of the user
        boolean firstIteration; // in the do-while body
        String menuMessage;
        String errorMessage;// is displayed when an invalid value is entered by  
                            //the user
                            
        menuMessage = "-----------------------------------------------\n\n" 
                    + "Choose one the following options:\n" 
                    + "1-   Add a new medicine\n" 
                    + "2-   Search for a medicine by name\n" 
                    + "3-   Search for a medicine by composition\n" 
                    + "4-   Sell a medicine\n" 
                    + "5-   Restock a medicine\n" 
                    + "6-   Display all medicines\n" 
                    + "7-   Display information\n" 
                    + "8-   Exist\n" 
                    + "Enter your choice (between 1 and 8): ";
        errorMessage = "Your choice must be a number between 1 and 8. \n"  
                     + "Please enter your choice again: ";

        firstIteration = true;
        do {
            System.out.print((firstIteration)? menuMessage : errorMessage);
            choice = sc.nextInt();
            firstIteration = false;
        } while ((choice>8) || (choice <1)); 
        return choice;
    }   

}
