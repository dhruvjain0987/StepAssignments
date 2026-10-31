package PracticeProblems.PPWeek4;

class Week4P3BusRoute {
    String routeCode, routeName;
    int priority;

    Week4P3BusRoute(String routeCode, String routeName, int priority) {
        this.routeCode = routeCode;
        this.routeName = routeName;
        this.priority = priority;
    }

    Week4P3BusRoute(String routeCode, String routeName) {
        this(routeCode, routeName, 0);
    }

    int compareTo(Week4P3BusRoute other) {
        if (priority != other.priority)
            return Integer.compare(other.priority, priority);

        int result = routeCode.compareToIgnoreCase(other.routeCode);
        if (result != 0) return result;

        return routeName.compareToIgnoreCase(other.routeName);
    }

    static Week4P3BusRoute[] rankRoutes(Week4P3BusRoute[] routes) {
        Week4P3BusRoute[] result = routes.clone();

        // Stable insertion sort: equal routes keep their original order.
        for (int i = 1; i < result.length; i++) {
            Week4P3BusRoute key = result[i];
            int j = i - 1;

            while (j >= 0 && result[j].compareTo(key) > 0) {
                result[j + 1] = result[j];
                j--;
            }

            result[j + 1] = key;
        }

        return result;
    }
}

public class Prob3 {
    public static void main(String[] args) {
        Week4P3BusRoute[] routes = {
            new Week4P3BusRoute("RT205L", "Airport Express", 3),
            new Week4P3BusRoute("rt201j", "City Central", 4),
            new Week4P3BusRoute("RT299T", "Night Service")
        };

        Week4P3BusRoute[] ranked =
            Week4P3BusRoute.rankRoutes(routes);

        for (Week4P3BusRoute r : ranked)
            System.out.println(r.routeCode);
    }
}