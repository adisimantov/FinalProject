package finalproject.finalproject.Model;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Anna on 05-Mar-16.
 */
public class Checkin {

    public enum TimePart {
        H00_02,
        H02_04,
        H04_06,
        H06_08,
        H08_10,
        H10_12,
        H12_14,
        H14_16,
        H16_18,
        H18_20,
        H20_22,
        H22_00;

        public static TimePart fromInt(int x) {
            switch(x) {
                case 0:
                    return H00_02;
                case 1:
                    return H02_04;
                case 2:
                    return H04_06;
                case 3:
                    return H06_08;
                case 4:
                    return H08_10;
                case 5:
                    return H10_12;
                case 6:
                    return H12_14;
                case 7:
                    return H14_16;
                case 8:
                    return H16_18;
                case 9:
                    return H18_20;
                case 10:
                    return H20_22;
                case 11:
                    return H22_00;
            }
            return null;
        }
    }

    private String type;
    private TimePart time;
    private int count;

    public Checkin(String type, TimePart time, int count) {
        this.type = type;
        this.time = time;
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public TimePart getTime() {
        return time;
    }

    public int getCount() {
        return count;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTime(TimePart time) {
        this.time = time;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
