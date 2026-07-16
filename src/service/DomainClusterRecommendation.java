package service;

import java.util.*;

public class DomainClusterRecommendation {

    // Domain → Cluster mapping
    static Map<String,Integer> clusterMap = new HashMap<>();

    static{

        // Cluster 1 → AI & Data
        clusterMap.put("Artificial Intelligence",1);
        clusterMap.put("Machine Learning",1);
        clusterMap.put("Deep Learning",1);
        clusterMap.put("Data Science",1);
        clusterMap.put("Big Data",1);

        // Cluster 2 → Security
        clusterMap.put("Cyber Security",2);
        clusterMap.put("Ethical Hacking",2);
        clusterMap.put("Network Security",2);

        // Cluster 3 → Cloud/Web
        clusterMap.put("Cloud Computing",3);
        clusterMap.put("Web Development",3);

    }

    public static List<String> recommendDomains(String currentDomain){

        List<String> recommendations = new ArrayList<>();

        if(!clusterMap.containsKey(currentDomain))
            return recommendations;

        int userCluster = clusterMap.get(currentDomain);

        for(String domain : clusterMap.keySet()){

            int cluster = clusterMap.get(domain);

            if(cluster == userCluster && !domain.equals(currentDomain)){

                recommendations.add(domain);

            }

        }

        return recommendations;

    }

}