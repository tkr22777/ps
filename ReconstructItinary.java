import java.util.*; 

class ReconstructItinary {

    /* https://leetcode.com/problems/reconstruct-itinerary/submissions/ */

    public static void main(String[] args) {
        /*{ {"EZE","TIA"},{"EZE","HBA"},{"AXA","TIA"},{"JFK","AXA"}, {"ANU","JFK"},
         *  {"ADL","ANU"},{"TIA","AUA"},{"ANU","AUA"},{"ADL","EZE"},{"ADL","EZE"},
         *  {"EZE","ADL"},{"AXA","EZE"},{"AUA","AXA"},{"JFK","AXA"},{"AXA","AUA"},
         *  {"AUA","ADL"},{"ANU","EZE"},{"TIA","ADL"},{"EZE","ANU"},{"AUA","ANU"}}
         */
    }

    public List<String> findItinerary(List<List<String>> tickets) {

        int totalTickets = tickets.size();
        Map<String, List<String>> departToArrive = new HashMap<String, List<String>>();

        for (List<String> ticket: tickets) {
            String depart = ticket.get(0);
            String arrive = ticket.get(1);

            List<String> arrivals = new ArrayList<String>();
            if (departToArrive.containsKey(depart)) {
                arrivals = departToArrive.get(depart);
            }
            arrivals.add(arrive);
            departToArrive.put(depart, arrivals);
        }

        departToArrive.keySet().forEach(k -> Collections.sort(departToArrive.get(k)));

        departToArrive.keySet().forEach(k -> System.out.println("Key: " + k + " Val: " + departToArrive.get(k).toString()));
        return findItineraryRecursive(departToArrive, "JFK", totalTickets);
    }

    public ArrayList<String> findItineraryRecursive(Map<String, List<String>> departToArrive, String depart, int totalTickets) {

        if (totalTickets == 0) {
            return new ArrayList<>(Arrays.asList(depart));
        }

        if (!departToArrive.containsKey(depart)) { //unacceptable base case
            return null;
        }

        List<String> arrivals = departToArrive.get(depart);

        List<String> arrivalCopy = new ArrayList<>(arrivals);

        ArrayList<String> anItin = null;

        for (String arrival: arrivalCopy) {

            arrivals.remove(arrival);

            anItin = findItineraryRecursive(departToArrive, arrival, totalTickets - 1);

            arrivals.add(arrival);

            if (anItin != null) {
                break;
            }
        }

        if (anItin == null) {
            return null;
        }

        anItin.add(0, depart);
        return anItin;
    }

}

