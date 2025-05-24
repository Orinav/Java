/**
 * The class Airport - Represents an airport of a city with a flight schedule and the number of the current flights on the schedule
 * @author Ori Nave
 * @version 14/04/2022
 */
public class Airport
{
    private Flight[] _flightsSchedule;
    private int _noOfFlights;
    private String _city;
    private static final int MAX_FLIGHTS = 200;


    /**
     * Constructor - Creates a new Airport object
     * @param city the city which the airport located in.
     */
    public Airport(String city)
    {
        _city = new String(city);
        _flightsSchedule = new Flight[MAX_FLIGHTS];
        _noOfFlights = 0;
    }


    /**
     * adding a flight to the flights schedule
     * @param f flight object that we want to add to the schedule.
     * @return true if the flight added successfully, false if not.
     */
    public boolean addFlight(Flight f)
    {
        if (_noOfFlights == MAX_FLIGHTS)
            return false;
        else if (_city.equals(f.getOrigin()) || _city.equals(f.getDestination()))
        {
            _flightsSchedule[_noOfFlights] = new Flight(f);
            _noOfFlights++;
            return true;
        }
        else
            return false;
    }

    /**
     * return the first index of a given flight
     * @param f flight object.
     * @return the first index of a given flight, if there are no flights or the flight isn't exist in the schedule it will return -1.
     */
    private int findFlight(Flight f)
    {
        int flightIndex;
        if (_noOfFlights == 0) // if there are no flights at all
            return -1;
        for (int i = 0; i < _noOfFlights; i++)
        {
            if (_flightsSchedule[i].equals(f))
            {
                flightIndex = i;
                return flightIndex; // the index of the desired f
            }
        }
        return -1; // didn't find the desired f
    }

    /**
     * removing a flight from the flight schedule
     * @param f flight object that we want to remove from the schedule.
     * @return true if removed successfully, false if not.
     */
    public boolean removeFlight(Flight f)
    {
        int flightIndex = findFlight(f);
        if (flightIndex == -1) //the flight is not in the schedule or there are no flights yet
            return false;

        _flightsSchedule[flightIndex] = null;
        for (; flightIndex < _noOfFlights && flightIndex + 1 < _noOfFlights; flightIndex++)
        {
            _flightsSchedule[flightIndex] = _flightsSchedule[flightIndex + 1];
        }
        _flightsSchedule[_noOfFlights - 1] = null;
        _noOfFlights--;
        return true;
    }

    /**
     * return the earliest time of the flight who departs from place(given parameter)
     * @param place a String that represents a city which the flight departs from.
     * @return the time of the first flight that departs from place, however if not found the method will return null.
     */
    public Time1 firstFlightFromOrigin(String place)
    {
        Time1 earliestTime = null;
        for (int i = 0; i < _noOfFlights; i++)
        {
            if (_flightsSchedule[i].getOrigin().equals(place))
            {
                if (earliestTime == null)
                    earliestTime = new Time1(_flightsSchedule[i].getDeparture()); //initializing earliestTime
                else if (_flightsSchedule[i].getDeparture().before(earliestTime))
                    earliestTime = new Time1(_flightsSchedule[i].getDeparture());
            }
        }
        return earliestTime; //if not initialized(no flight departs from place), it will return null
    }

    /**
     * return the number of the flights that are full.
     * @return the number of the flights that are full.
     */
    public int howManyFullFlights()
    {
        int counter = 0;
        for (int i = 0; i < _noOfFlights; i++)
        {
            if (_flightsSchedule[i].getIsFull())
                counter++;
        }
        return counter;
    }

    /**
     * return how many flights with an origin or a destination of place(a given parameter)
     * @param place a String that represents a city that can be an origin or a destination.
     * @return a number that represents how many flights origin or destination is equal the given place/city.
     */
    public int howManyFlightsBetween(String place) {
        int counter = 0;
        for (int i = 0; i < _noOfFlights; i++)
        {
            if (_flightsSchedule[i].getOrigin().equals(place) || _flightsSchedule[i].getDestination().equals(place))
                counter++;
        }
        return counter;
    }

    /**
     * return the most common destination
     * @return the most common destination, however if there are no flights at all, the method will return null.
     */
    public String mostPopularDestination()
    {
        if (_noOfFlights == 0)
            return null;

        int counter = 0;
        int maxCounter = 0;
        String mostPop = new String(_flightsSchedule[0].getDestination());
        for (int i = 0; i < _noOfFlights; i++)
        {
            counter = 0;
            for (int j = 0; j < _noOfFlights; j++)
            {
                if (_flightsSchedule[i].getDestination().equals(_flightsSchedule[j].getDestination()))
                    counter++;
            }
            if (counter > maxCounter)
            {
                maxCounter = counter;
                mostPop = new String(_flightsSchedule[i].getDestination());
            }
        }
        return mostPop;
    }

    /**
     * return the flight which its ticket price cost the most
     * @return the flight which its ticket price cost the most, however if there are no flights at all, the method will return null.
     */
    public Flight mostExpensiveTicket()
    {
        if (_noOfFlights == 0)
            return null;

        Flight expFlight = new Flight(_flightsSchedule[0]);
        int highest_price = -1;
        for (int i = 0; i < _noOfFlights; i++)
        {
            if (_flightsSchedule[i].getPrice() > highest_price)
            {
                highest_price = _flightsSchedule[i].getPrice();
                expFlight = new Flight(_flightsSchedule[i]);
            }
        }
        return expFlight;
    }

    /**
     * return the flight with the longest flight duration.
     * @return the flight with longest flight duration, however if there are no flights at all, the method will return null.
     */
    public Flight longestFlight()
    {
        if (_noOfFlights == 0)
            return null;

        int longestTime = 0;
        Flight longestFlight = new Flight(_flightsSchedule[0]);
        for (int i = 0; i < _noOfFlights ; i++)
        {
            if (_flightsSchedule[i].getFlightDuration() > longestTime)
            {
                longestTime = _flightsSchedule[i].getFlightDuration();
                longestFlight = new Flight(_flightsSchedule[i]);
            }
        }
        return longestFlight;
    }

    /**
     * return a String representation of the Airport object
     * @return a String representation of the Airport object, however if there are no flights at all, the method will return null.
     */
    public String toString()
    {
       /* if (_noOfFlights == 0)
            return null;

        System.out.println("The flights for airport " + _city + " today are:");
        for (int i = 0; i < _noOfFlights; i++)
        {
            if (i == _noOfFlights - 1) //if it's the last object don't print a blank line after
                System.out.print(_flightsSchedule[i]);
            else
                System.out.println(_flightsSchedule[i]);
        }
        return "";*/
        if(_noOfFlights==0) return null;
        String str = "The flights for airport " + _city + " today are:\n";
        for(int i=0; i<_noOfFlights; i++)
        {            
            str +=_flightsSchedule[i]+"\n"; 
        }
        return str;
    }
}

