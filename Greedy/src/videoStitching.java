import java.util.Arrays;
import java.util.Comparator;

/**
 * @author psj
 * @date 2022/3/11 11:08
 * @File: videoStitching.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/video-stitching/

public class videoStitching {
    public int videoStitching(int[][] clips, int time) {
        // 先按起点从小到大排序，假设要凑出[1,5]的视频，肯定需要start<=1的视频
        // 如果起点相同再按终点找出终点最大的，假设起点相同的话找一个长的视频(即终点最大)就可以保证需要的视频少
        // 假设按照上述排序规则排好后视频如下所示：
        // video[0]: ---------------------
        // video[1]: -------------
        // video[2]:     -----------
        // video[3]:         --------------------------
        // video[4]:             -----------------
        // video[5]:                         -----------------
        // 第一个选择video[0]后，在所有和video[0]重叠的视频中选择一个终点最大的视频(即video[3])
        // 假设选择的是video[4]不是video[3]，则会少一段视频，后续可能会以此需要更多的视频取补，为什么不找一个更长的，长了剪掉就是
        // 再通过vide[3]根据上述的贪心策略选出第三个，以此类推直到覆盖区间[0,time]

        if (time == 0) {
            return 0;
        }
        Arrays.sort(clips, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o2[1] - o1[1];
                }

                return o1[0] - o2[0];
            }
        });

        int result = 0;
        int n = clips.length;
        int i = 0;  // 表示当前遍历到的视频下标
        int curEnd = 0;  // 表示当前选中作为需要视频的end值
        int nextEnd = 0;
        // 第一次while循环中直接判断clips[0][0]是否小于等于0(即curEnd)是为了保证排序后所有区间中最小的start值是否小于0
        // 如果小于0就根本剪不出[0,time]的视频
        while (i < n && clips[i][0] <= curEnd) {
            while (i < n && clips[i][0] <= curEnd) {
                // 找出和当前选中的视频重叠的且end值最大的视频
                nextEnd = Math.max(nextEnd, clips[i][1]);
                i++;
            }
            result++;
            curEnd = nextEnd;  // 选中的下一个视频作为下一轮的当前视频
            // 说明可以拼接出区间[0,time]
            if (curEnd >= time) {
                return result;
            }
        }

        return -1;
    }
}
