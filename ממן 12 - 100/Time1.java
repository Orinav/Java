/**
 * Represents time - hours:minutes. Coordinates cannot be negative.
 * @author Ori Nave
 * @version 30/03/2022
 */
public class Time1
{
    private int _hour;
    private int _minute;
    private static final int MIN = 0;
    private final static int MAX_HOUR = 23;
    private final static int MAX_MINUTE = 59;
    private static final int ONE_MINUTE = 60;
    private static final int DAY = 24;

    /**
     * Constructs a Time1 object.
     * @param h the hour of the time
     * @param m the minute of the time
     */
    public Time1(int h, int m)
    {
        if (h < MIN || h > MAX_HOUR)
            _hour = MIN;
        else
            _hour = h;
        if (m < MIN || m > MAX_MINUTE)
            _minute = MIN;
        else
            _minute = m;
    }

    /**
     * Copy constructor for Time1. Construct a time with the same instance variables as another time.
     * @param other The time object from which to construct the new time
     */
    public Time1(Time1 other)
    {
        this(other._hour, other._minute);
    }

    /**
     * Returns the hour of the time.
     * @return The hour of the time
     */
    public int getHour()
    {
        return _hour;
    }

    /**
     * Returns the minute of the time.
     * @return The minute of the time
     */
    public int getMinute()
    {
        return _minute;
    }

    /**
     * Changes the hour of the time. If an illegal number is received hour will be unchanged.
     * @param num The new hour
     */
    public void setHour(int num)
    {
        if (num >= MIN && num <= MAX_HOUR)
            _hour = num;
    }

    /**
     * Changes the minute of the time. If an illegal number is received minute will be unchanged.
     * @param num The new minute
     */
    public void setMinute(int num)
    {
        if (num >= MIN && num <= MAX_MINUTE)
            _minute = num;
    }

    /**
     * Return a string representation of this time (hh:mm).
     * @return String representation of this time (hh:mm).
     */
    public String toString()
    {
        if (_hour/10 == MIN && _minute/10 == MIN) // if the hour and the minute are single char, we add 0 on their left
            return "0" + _hour + ":" + "0" + _minute;
        else if (_hour/10 == MIN) // if only the hour is a single char, we add 0 on its left
            return "0" + _hour + ":" + _minute;
        else if (_minute/10 == MIN) // if only the minute is a single char, we add 0 on its left
            return _hour + ":" + "0" + _minute;
        else //if none of them is a single char, we will print them as they are
            return _hour + ":" + _minute;
    }

    /**
     * Return the amount of minutes since midnight.
     * @return amount of minutes since midnight.
     */
    public int minFromMidnight()
    {
        return _hour*ONE_MINUTE + _minute;
    }

    /**
     * Check if the received time is equal to this time.
     * @param other The time to be compared with this time
     * @return true if the received time is equal to this time
     */
    public boolean equals(Time1 other)
    {
        return _hour == other._hour && _minute == other._minute;
    }

    /**
     * Check if this time is before a received time.
     * @param other The time to check if this point is before
     * @return true if this time is before other time
     */
    public boolean before(Time1 other)
    {
        if (_hour == other._hour && _minute == other._minute) //same hour, same minute
            return false;
        else if (_hour == other._hour && _minute > other._minute) //same hour, later minute
            return false;
        else if (_hour == other._hour && _minute < other._minute) //same hour, earlier minute
            return true;
        else if (_hour > other._hour) //later hour, regardless of minute
            return false;
        else
            return true;
    }

    /**
     * Check if this time is after a received time.
     * @param other The time to check if this point is after
     * @return true if this time is after other time
     */
    public boolean after(Time1 other)
    {
        return other.before(this);
    }

    /**
     * Calculates the difference (in minutes) between two times. Assumption: this time is after other time.
     * @param other The time to check the difference to
     * @return int difference in minutes
     */
    public int difference(Time1 other)
    {
        return (_hour - other._hour)*ONE_MINUTE + (_minute - other._minute);
    }

    /**
     * Copy current object and add requested minutes to new object.
     * @param num The minutes need to add.
     * @return new update Time1 object.
     */
    public Time1 addMinutes(int num)
    {
        if ((_minute + num) > MAX_MINUTE) // if we need to add hour/hours
            return new Time1((_hour + (_minute + num) / ONE_MINUTE) % DAY, (_minute + num) % ONE_MINUTE); // the meaning of 60 is regards to minutes, the meaning of 24 regards to the hour
        else if ((_minute + num) != MIN && (_minute + num) % ONE_MINUTE == MIN) // if we need to decrease hour/hours but to add 1 to it
        {
            if ((_hour + 1 - Math.abs(_minute + num - ONE_MINUTE) / ONE_MINUTE) < MIN) // if the hour below 0
                return new Time1(DAY + (_hour + 1 - Math.abs(_minute + num - ONE_MINUTE) / ONE_MINUTE) % DAY, ONE_MINUTE - Math.abs(_minute + num) % ONE_MINUTE);
            else
                return new Time1(_hour + 1 - Math.abs(_minute + num - ONE_MINUTE) / ONE_MINUTE, ONE_MINUTE - Math.abs(_minute + num) % ONE_MINUTE);
        }
        else if ((_minute + num) < MIN) // if we need to decrease hour/hours
        {
            if ((_hour - Math.abs(_minute + num - ONE_MINUTE) / ONE_MINUTE) < MIN) //if the hour below 0
                return new Time1(DAY + (_hour - Math.abs(_minute + num - ONE_MINUTE) / ONE_MINUTE) % DAY, ONE_MINUTE - Math.abs(_minute + num) % ONE_MINUTE);
            else
                return new Time1(_hour - Math.abs(_minute + num - ONE_MINUTE) / ONE_MINUTE, ONE_MINUTE - Math.abs(_minute + num) % ONE_MINUTE);
        }
        else //no need to change the hour, just the minutes
            return new Time1(_hour, _minute + num);
    }

}
