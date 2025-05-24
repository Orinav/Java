/**
 * Represents time - hours:minutes. Coordinates cannot be negative.
 * @author Ori Nave
 * @version 30/03/2022
 */
public class Time2
{
    private int _minFromMid;
    private static final int MIN = 0;
    private final static int MAX_HOUR = 23;
    private final static int MAX_MINUTE = 59;
    private static final int ONE_MINUTE = 60;
    private static final int DAY = 24;

    /**
     * Constructs a Time2 object.
     * @param h the hour of the time
     * @param m the minute of the time
     */
    public Time2(int h, int m)
    {
        if (h < MIN || h > MAX_HOUR)
            _minFromMid += MIN;
        else
            _minFromMid += h * ONE_MINUTE;
        if (m < MIN || m > MAX_MINUTE)
            _minFromMid += MIN;
        else
            _minFromMid += m;
    }

    /**
     * Copy constructor for Time2. Constructs a time with the same variables as another time.
     * @param other The time object from which to construct the new time.
     */
    public Time2(Time2 other)
    {
        _minFromMid = other._minFromMid;
    }

    /**
     * Returns the hour of the time.
     * @return The hour of the time
     */
    public int getHour()
    {
        return _minFromMid / ONE_MINUTE;
    }

    /**
     * Returns the minute of the time.
     * @return The minute of the time
     */
    public int getMinute()
    {
        return _minFromMid % ONE_MINUTE;
    }

    /**
     * Changes the hour of the time. If an illegal number is received hour will remain unchanged.
     * @param num The new hour
     */
    public void setHour(int num)
    {
        if (num >= MIN && num <= MAX_HOUR)
            _minFromMid = _minFromMid % ONE_MINUTE + num * ONE_MINUTE;
    }

    /**
     * Changes the minute of the time. If an illegal number is received minute will remain unchanged.
     * @param num The new minute
     */
    public void setMinute(int num)
    {
        if (num >= MIN && num <= MAX_MINUTE)
            _minFromMid = (_minFromMid / ONE_MINUTE)*ONE_MINUTE + num;
    }

    /**
     * Return the amount of minutes since midnight.
     * @return amount of minutes since midnight.
     */
    public int minFromMidnight()
    {
        return _minFromMid;
    }

    /**
     * Checks if the received time is equal to this time.
     * @param other The time to be compared with this time
     * @return true if the received time is equal to this time
     */
    public boolean equals(Time2 other)
    {
        return _minFromMid == other._minFromMid;
    }

    /**
     * Checks if this time is before a received time.
     * @param other The time to check if this time is before
     * @return true if this time is before other time
     */
    public boolean before(Time2 other)
    {
        if (_minFromMid == other._minFromMid)
            return false;
        else if (_minFromMid > other._minFromMid)
            return false;
        else
            return true;
    }

    /**
     * Checks if this time is after a received time.
     * @param other The time to check if this time is after
     * @return true if this time is after other time
     */
    public boolean after(Time2 other)
    {
        return other.before(this);
    }

    /**
     * Calculates the difference (in minutess) between two times.
     * @param other The time to check the difference with. Assumption: this time is after other time
     * @return int difference in minutes
     */
    public int difference(Time2 other)
    {
        return _minFromMid - other._minFromMid;
    }

    /**
     * Returns a string representation of this time(hh:mm).
     * @return String representation of this time(hh:mm).
     */
    public String toString()
    {
        if ((_minFromMid / ONE_MINUTE) / 10 == MIN && ((_minFromMid % ONE_MINUTE) / 10) == MIN)
            return "0" + _minFromMid / ONE_MINUTE + ":" + "0" + _minFromMid % ONE_MINUTE;
        else if ((_minFromMid / ONE_MINUTE) / 10 == MIN)
            return "0" + _minFromMid / ONE_MINUTE + ":" + _minFromMid % ONE_MINUTE;
        else if ((_minFromMid % ONE_MINUTE) / 10 == MIN)
            return "" + _minFromMid / ONE_MINUTE + ":" + "0" + _minFromMid % ONE_MINUTE;
        else
            return "" + _minFromMid / ONE_MINUTE + ":" + _minFromMid % ONE_MINUTE;
    }

    /**
     * Copy current object and add requested minutes to new object.
     * @param num The minutes need to add.
     * @return new update Time2 object.
     */
    public Time2 addMinutes(int num)
    {
        if ((_minFromMid % ONE_MINUTE + num) > MAX_MINUTE) // if we need to add hour/hours
            return new Time2((_minFromMid / ONE_MINUTE + (_minFromMid % ONE_MINUTE + num) / ONE_MINUTE) % DAY, (_minFromMid % ONE_MINUTE + num) % ONE_MINUTE); // the meaning of 60 is regards to minutes, the meaning of 24 regards to the hour
        else if ((_minFromMid % ONE_MINUTE + num) != MIN && (_minFromMid % ONE_MINUTE + num) % ONE_MINUTE == MIN) // if we need to decrease hour/hours but to add 1 to it
        {
            if ((_minFromMid / ONE_MINUTE + 1 - Math.abs(_minFromMid % ONE_MINUTE + num - ONE_MINUTE) / ONE_MINUTE) < MIN) // if the hour below 0
                return new Time2(DAY + (_minFromMid / ONE_MINUTE + 1 - Math.abs(_minFromMid % ONE_MINUTE + num - ONE_MINUTE) / ONE_MINUTE) % DAY, ONE_MINUTE - Math.abs(_minFromMid % ONE_MINUTE + num) % ONE_MINUTE);
            else
                return new Time2(_minFromMid / ONE_MINUTE + 1 - Math.abs(_minFromMid % ONE_MINUTE + num - ONE_MINUTE) / ONE_MINUTE, ONE_MINUTE - Math.abs(_minFromMid % ONE_MINUTE + num) % ONE_MINUTE);
        }
        else if ((_minFromMid % ONE_MINUTE + num) < MIN) // if we need to decrease hour/hours
        {
            if ((_minFromMid / ONE_MINUTE - Math.abs(_minFromMid % ONE_MINUTE + num - ONE_MINUTE) / ONE_MINUTE) < MIN) //if the hour below 0
                return new Time2(DAY + (_minFromMid / ONE_MINUTE - Math.abs(_minFromMid % ONE_MINUTE + num - ONE_MINUTE) / ONE_MINUTE) % DAY, ONE_MINUTE - Math.abs(_minFromMid % ONE_MINUTE + num) % ONE_MINUTE);
            else
                return new Time2(_minFromMid / ONE_MINUTE - Math.abs(_minFromMid % ONE_MINUTE + num - ONE_MINUTE) / ONE_MINUTE, ONE_MINUTE - Math.abs(_minFromMid % ONE_MINUTE + num) % ONE_MINUTE);
        }
        else //no need to change the hour, just the minutes
            return new Time2(_minFromMid / ONE_MINUTE, _minFromMid % ONE_MINUTE + num);
    }

}