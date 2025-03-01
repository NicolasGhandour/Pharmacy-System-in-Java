/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nicolas_ghandour;

/**
 * This class simulates a pharmacy that adds, sells and restocks medicines
 * of both types prescription and over the counter.
 * @version 1.1 April 2023
 * @author Nicolas Ghandour
 */
public class Pharmacy {

    /*represents the name of the pharmacy.*/
    private String name ;   
    
    /*array representing the medicines of the pharmacy*/
    private Medicine [] medicines;
    
    /* represents the total quantity of all the over the counter medicines. It's
     * incremented by a quantity each time an existing over the counter medicine  
     * in the pharmacy is restocked and it is decremented by a quantity each 
     * time an existing over the counter medicine in the pharmacy is sold.
     */
    private int overTheCounterQuantity;
    
    /* represents the total quantity of all the prsecription medicines. It's
     * incremented by a quantity each time an existing over the counter medicine  
     * in the pharmacy is restocked and it is decremented by a quantity each 
     * time an existing prescription medicine in the pharmacy is sold.
     */
    private int prescriptionQuantity;
    
    /*represents the number of medicines.
     *It is incremented each time a new medicine is added to the phramacy and
     *is decremented each time a medicine is completely sold(it quantity is 0)
     */
    private int numberOfMedicines; 
 
    private static int maxNumberOfMedicines;
    
    /**  
     * public constructor creates an pharmacy object by creating an Medicine  
     * array with 100 as a capacity
     * @param name represents the name of the pharmacy
     */
    public Pharmacy(String name) {
        setName(name);   // invoke the setter to set the name of the pharmacy
        setMaxNumberOfMedicines(100);
        medicines = new Medicine[maxNumberOfMedicines]; 
        // overTheCounterQuantity = 0;    
        // prescriptionQuantity = 0;       
        // numberOfMedicines = 0;         
    }

    /** 
    * method that will accept a new medicine of any type and 
    * checks if the medicine already exists in the array of medicines or not.
    * there are 3 cases:
    * 1 - If the medicine already exists in the array, the method will display a 
    * message telling the user that the medicine already exists and the 
    * array remains unchanged.
    * 2 - If the array is full the method should 
    * display an error message telling the user that the medicine could not 
    * be added 
    *3 - If the new medicine does not exist in the array the method will add 
    *this medicine to the array of medicines and adjusts the 
    *corresponding data fields.
    * 
    * It is used a nested if else since the conditions are all dependent to each 
    * other.
    *@param medicine representing the medicine we want to add to the Medicine 
    * array
    */
    public void addMedicine(Medicine medicine) {
        int index = searchByNameAndDose(medicine.getName(),
                                        medicine.getDose());
        int quantityOfMedicine = medicine.getQuantity();

        if (index != -1){                                      /* first case*/
            System.out.println("medicine already exists ");
        } else if(numberOfMedicines == maxNumberOfMedicines) { /*second case*/
            System.out.println("The medicine cannot be added.");
        } else {                                                /*third case*/
            medicines[numberOfMedicines] = medicine; //assign this medicine(we 
                    //want to add) to the first variable in the Medicine array 
                    //that is not referencing to any medicine
            numberOfMedicines++ ; //incremetning the number of medicines.
            
            /*Printing the result*/
            System.out.println("New medicine is added successfully ");
            
            /*checking the type of the medicine in order to know which of the  
            datafield we need  to increment*/
            if(medicine instanceof Prescription){// 
                prescriptionQuantity += quantityOfMedicine;
            } else { // the medicine can only be of 2 types: if it is not of 
                     // type prescription, it is definitely over the counter.
                overTheCounterQuantity += quantityOfMedicine;
            }
            
            
        }
    }

   /** 
    * This methods raise the prices of the of all the medicines by a specific 
    * percentage. If the percentage is negative the method will reduce the 
    * price by the specified percentage.
    * @param percentage as a double representing the percentage by which we want
    * to raise the prices of the medicines
    */
    public void raisePrices(double percentage) {
        for(int counter = 0; counter < numberOfMedicines ; counter++) {
            medicines[counter].setPrice ( medicines[counter].getPrice()
                                        *(1 + percentage/100) );
        }
    }
    /** 
    *This method searches in the medicines array for medicines that matches a name
    * of a medicine 
    *@param name represents the name of the medicine we are searching
    *@return the array indexesOfMatchingNames.(indexes of the medicines in the 
    *medicines array)
    */
    public int[] searchByName(String name) {
        int [] temporary = new int[numberOfMedicines]; 
        int counterTemp = 0; // counter for the temporary array that is 
                             // incremented each time an int value 
                             // (representing the index) is saved to the 
                             // temporary array
                             
        for (int counter = 0; counter < numberOfMedicines ; counter++)
            if ((medicines[counter].getName()).equals(name)) {
                temporary [counterTemp] = counter;
                counterTemp++ ;
            }
        
        int [] indexesOfMatchingNames = new int [counterTemp];
        
        /* Copying the indexes from the temporary array to the  
        indexesOfMatchingNames array*/
        for(int count = 0 ; count< counterTemp ; count ++) {
            indexesOfMatchingNames[count] = temporary[count];
        }
        return indexesOfMatchingNames;
    }

    /** 
    * This method searches in the medicines array for medicines that matches 
    * both the name and the dose of a medicine
    * of a medicine 
    * @param name represents the name of the medicine we are searching
    * @param dose represents the dose of the medicine we are searching
    * @return the index of the medicine in the medicines array (-1 if the 
    * medicine matching both the name and the dose was not found )
    */
    public int searchByNameAndDose (String name, int dose) {
        int [] indexesOfMatchingNames =  searchByName(name);
        
        /*return the index of the madecine matching both the name and the dose 
        we are searching in the medicines array */
        for (int count = 0; count< indexesOfMatchingNames.length ; count++) {
            if (medicines[indexesOfMatchingNames[count]].getDose() == dose) {
                return indexesOfMatchingNames[count]; 
            }
        }
        
        /* if the medicine was not found */
        return -1;    
    }

    /**
    * This method searches in the medicines array for medicines that matches a 
    * composition of a medicine. This method works in the exactly same way the 
    * searchByName method works.
    * @param composition represents the composition of the medicine we are 
    * searching
    * @return the array indexesOfMatchingComposition.(indexes of the medicines 
    * int the medicines array)
    */
    public int [] searchByComposition (String composition) {
        int [] temporary = new int [medicines.length];
        int countTemp = 0 ;
        
        for (int counter = 0; counter < numberOfMedicines; counter++)
            if ((medicines[counter].getComposition())
                  .equals(composition)){
                temporary [countTemp] = counter;
                countTemp++ ;
            }
        
        /* Copying the indexes from the temporary array to the result array */
        int [] indexesOfMatchingComposition = new int [countTemp] ;
        
        for (int count = 0; count< countTemp; count ++) {
            indexesOfMatchingComposition[count] = temporary[count];
        }
        
        return indexesOfMatchingComposition;
    }

    /** 
    * This method will accept the name and dose of the 
    * medicine and the required quantity. The method will search for a medicine 
    * by its specified name and dose. 
    * there are 3 cases:
    * 1 - If the medicine is not found the method will display a message 
    * telling the user that the medicine is not found.
    * 2 - If the medicine is found but the available quantity is less than the 
    * required quantity the method will display a message telling the user 
    * that available quantity is not enough. The array medicines remains 
    * unchanged.
    * 3 - If the medicine is found and the available quantity is greater than 
    * the required quantity, the quantity of this medicine will be reduced 
    * and a message will be displayed telling the user that selling is 
    * successful. 
    * It is used a nested if else since the conditions are all dependent to each
    * other.
    * @param name representing the name of the medicine we want to sell
    * @param dose representing the dose of the medicine we want to sell
    * @param quantity representing the quantity of the medicine we want to sell
    */
    public void sellMedicine(String name, int dose, int quantity) {                
        int index = searchByNameAndDose(name,dose) ;
        
        if (index == -1) {                                       /*first case*/
            System.out.println("Medicine not found");
        } else if (quantity > (medicines[index].getQuantity())) {/*second case*/
            System.out.println("The available quantity is not enough");
        } else {                                                /*third case*/
            medicines[index].setQuantity(medicines[index].getQuantity() 
                                         - quantity);
            
            /*Printing the result*/
            System.out.println("Selling is successful ");
            
            /*checking the type of the medicine in order to know which of the  
            datafield we need  to decrement*/
            if(medicines[index] instanceof Prescription){ 
                prescriptionQuantity-= quantity;
            } else { // the medicine can only be of 2 types: if it is not of 
                     // type prescription, it is definitely over the counter.
                overTheCounterQuantity-= quantity;
            }
            
            /*if the quantity is 0, it means that the medicine is not existing
            anymore*/                               
            if (medicines[index].getQuantity()==0) {
                medicines[index] = medicines[numberOfMedicines-1];//assigning 
                        //the reference of the medicine whose quntity became 0   
                        //to the last medicine in the medicines Array
                medicines[numberOfMedicines-1] = null;// since the medicine at 
                                                      //[numberOfMedicines-1] is 
                                                      //already referenced by 
                                                      //medicines[index]
                numberOfMedicines--; 
            }   
        }
    }

   /**
    * method that will accept the name and dose of a medicine and 
    * search the array of medicines to check if this medicine already exists or 
    * not. There are 2 case:
    * 1 - If the medicine does not exist in the array the method will return 
    * false.
    * 2 - If the medicine already exists in the array, the method will add the 
    * new quantity to the available quantity of this medicine and return true.
    * 
    * @param name represents the name of the medicine we want to restock
    * @param dose represents the dose of the medicine we want to restock
    * @param quantity represents the quantity of the medicine we want to restock
    * @return true if the restock is successful and false otherwise
    */
    public boolean restock(String name,int dose,int quantity){
        int index = searchByNameAndDose(name,dose);
        
        if (index == -1) {            /*first case*/
            System.out.println("Medicine not found. ");
            return false; 
        } // else {                  /*second case*/
            medicines[index].setQuantity(medicines[index].getQuantity() 
                                        + quantity);
            
            /*Printing the message*/
            System.out.println("Restock successful");
            
            /*checking the type of the medicine in order to know which of the  
            datafield we need  to increment*/
            if (medicines[index] instanceof Prescription){
                prescriptionQuantity+=quantity;
            } else { // the medicine can only be of 2 types: if it is not of 
                     // type prescription, it is definitely over the counter.
                overTheCounterQuantity += quantity;
            }
         return true;
      //} 
    }

                    /* Accessors */
     
    public Medicine[] getMedicines() {
        return medicines;
    }   

    public int getOverTheCounterQuantity() {
        return overTheCounterQuantity;
    }

    public int getPrescriptionQuantity() {
        return prescriptionQuantity;
    }

    public int getNumberOfMedicines() {
        return numberOfMedicines;
    }

    public static int getMaxNumberOfMedicines() {
        return maxNumberOfMedicines;
    }

                    /* Mutators */

    public void setName(String name) {
        this.name = name;
    }

    public static void setMaxNumberOfMedicines(int maxNumberOfMedicines) {//this 
            //setter is static since the data field that it is setting is static
        Pharmacy.maxNumberOfMedicines = (maxNumberOfMedicines > 0)
                                      ? maxNumberOfMedicines
                                      : 100;
    }

    /**
     * toString method used to display information about a pharmacy
     * @return a String having the information.
     */
    public String toString(){
        return  "Pharmacy: " + name + "\nNumber of Medicines: "
                + numberOfMedicines + "\nOver the counter quantity: " 
                + overTheCounterQuantity + "\nPrescription quantity: " 
                + prescriptionQuantity + "\n" ;
    }


}

