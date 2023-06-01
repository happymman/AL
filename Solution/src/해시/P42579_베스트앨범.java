package 해시;

import java.util.*;

public class P42579_베스트앨범 {

    static class Music{
        int id;
        int plays;

        public Music(int id, int plays){
            this.id = id;
            this.plays = plays;
        }

        public int getId(){
            return this.id;
        }

        public int getPlays(){
            return this.plays;
        }
    }

    public int[] solution(String[] genres, int[] plays) {

        List<Integer> bestAlbum = new ArrayList<>();
        Map<String, Integer> totalMap = new HashMap<>();
        Map<String, List<Music>> musicListMap = new HashMap<>();

        for(int i=0;i<genres.length;i++){
            int sum = totalMap.getOrDefault(genres[i],0);
            totalMap.put(genres[i], sum+plays[i]);

            if(musicListMap.containsKey(genres[i])){
                musicListMap.get(genres[i]).add(new Music(i, plays[i]));
            }else{
                List<Music> list = new ArrayList<>();
                list.add(new Music(i, plays[i]));
                musicListMap.put(genres[i], list);
            }
        }

        //total 정렬
        List<Map.Entry<String,Integer>> totalList = new ArrayList<>(totalMap.entrySet());
        Collections.sort(totalList, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        for(List<Music> musicList : musicListMap.values()){
            Collections.sort(musicList, (o1, o2) -> o2.getPlays() -o1.getPlays());
        }

        for(Map.Entry<String,Integer> total : totalList){
            List<Music> musicList = musicListMap.get(total.getKey());
            for(int i=0;i<musicList.size();i++){
                if(i==2) break;
                bestAlbum.add(musicList.get(i).getId());
            }
        }

        return bestAlbum.stream().mapToInt(Integer::intValue).toArray();
    }
}