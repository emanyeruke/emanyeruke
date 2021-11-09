package zw.co.mynhaka.polad.service.util;

import java.util.function.IntPredicate;

public class Constants {

    /*
    Accident Policy Constants
     */
    public static final int PRINCIPAL_ENTRY_AGE_ACCIDENT = 60;
    public static final int SPOUSE_ENTRY_AGE_ACCIDENT = 60;

    public static final IntPredicate iPRINCIPAL_ENTRY_AGE_ACCIDENT = i -> i < 60;
    /*
    Savings Policy Constants
     */
    public static final int PRINCIPAL_ENTRY_AGE_SAVINGS = 60;
    public static final int PREMIUM_WAIVER_WAITING_PERIOD_SAVINGS = 6;


    /*
    Funeral Policy Constants
     */
    public static final int PRINCIPAL_ENTRY_AGE_FUNERAL = 65;
    public static final int MAX_PARENTS_FUNERAL = 4;
    public static final int MAX_EXTENDED_FUNERAL = 4;
    public static final int SPOUSE_ENTRY_AGE_FUNERAL = 55;
    public static final int PARENT_ENTRY_AGE_FUNERAL = 75;
    public static final int EXTENDED_ENTRY_AGE_FUNERAL = 65;
    public static final int CHILD_ENTRY_AGE_FUNERAL = 23;
    public static final int SPOUSE_WAITING_PERIOD_FUNERAL = 3;
    public static final int CHILD_WAITING_PERIOD_FUNERAL = 3;
    public static final int OTHER_WAITING_PERIOD_FUNERAL = 6;

/*
Comprehensive Policy Constants
 */

    /*public static final int GLOBAL_LIMIT = 25000;
    public static final int MAX_PARENTS_FUNERAL = 4;
    public static final int MAX_EXTENDED_FUNERAL = 4;
    public static final int SPOUSE_ENTRY_AGE_FUNERAL = 55;
    public static final int PARENT_ENTRY_AGE_FUNERAL = 75;
    public static final int EXTENDED_ENTRY_AGE_FUNERAL = 65;
    public static final int CHILD_ENTRY_AGE_FUNERAL = 23;
    public static final int SPOUSE_WAITING_PERIOD_FUNERAL = 3;
    public static final int CHILD_WAITING_PERIOD_FUNERAL = 3;
    public static final int OTHER_WAITING_PERIOD_FUNERAL = 6;*/


}


