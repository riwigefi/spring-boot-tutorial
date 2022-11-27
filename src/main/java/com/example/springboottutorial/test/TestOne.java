package com.example.springboottutorial.test;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TestOne {

    public static void main(String[] args) {
        // 老公组
        List<Couple> husbands = new ArrayList<>();
        husbands.add(new Couple(1, "梁山伯"));
        husbands.add(new Couple(2, "牛郎"));
        husbands.add(new Couple(3, "干将"));
        husbands.add(new Couple(4, "工藤新一"));
        husbands.add(new Couple(5, "罗密欧"));

        // 老婆组
        List<Couple> wives = new ArrayList<>();
        wives.add(new Couple(1, "祝英台"));
        wives.add(new Couple(2, "织女"));
        wives.add(new Couple(3, "莫邪"));
        wives.add(new Couple(4, "毛利兰"));
        wives.add(new Couple(5, "朱丽叶"));
    }

    public void matchAndLog1( List<Couple> husbands, List<Couple> wives, int count ) {
        /**
         * 简单粗暴两个循环，代码非常直观，外层遍历husbands，内层根据husband的familyId在wives遍历里去匹配
         * 但是循环次数过多,如果男女cp各个1000人，那么全部匹配需要1000*1000=100w次循环
         * 我们要明确，在当前这个需求中，每位男嘉宾只能选一位女嘉宾，外层for循环到牛郎，内层for循环到织女
         * 一旦牛郎织女配对后，内层for循环就没必要继续遍历了，因为只有织女和牛郎配对
         */
        for(Couple husband: husbands) {
            for(Couple wife: wives) {
                // 记录循环次数
                count++;
                if(husband.getFamilyId().equals(wife.getFamilyId())) {
                    System.out.println(husband.getUserName() + "爱" + wife.getUserName());
                }
            }
        }
    }


    public void matchAndLog2( List<Couple> husbands, List<Couple> wives, int count ) {
        /**
         * 一旦牛郎织女配对后，内层for循环就没必要继续遍历了，因为只有织女和牛郎配对
         * 此时就推出当前的内层循环，换下一轮的外层男嘉宾
         * 不过这样有优化空间，比如即使牛郎和织女匹配成功了，换干将遍历wives时，还是要遇到织女，还是要匹配一次
         * 在上一轮循环中，牛郎和织女已经匹配了，所以这轮循环，还去匹配织女是没有意义的
         */
        for(Couple husband: husbands) {
            for(Couple wife: wives) {
                // 记录循环次数
                count++;
                if(husband.getFamilyId().equals(wife.getFamilyId())) {
                    System.out.println(husband.getUserName() + "爱" + wife.getUserName());
                    // 牵手成功
                    break;
                }
            }
        }
    }

    public void matchAndLog3( List<Couple> husbands, List<Couple> wives, int count ) {
        /**
         * 一旦牛郎织女配对后，内层for循环就没必要继续遍历了，同时把织女从wives list中清出去
         * 这样下轮循环时，就不会发生无意义的匹配了
         * 不过还不是最好的，因为平均性能不高
         * 对于某些算法而言，元素的排列顺序会改变算法的复杂度，在数据结构于算法中，对一个算法往往有三个衡量唯独：
         * - 最好复杂度
         * - 平均复杂度
         * - 最坏复杂度
         * 现实生活中，我们往往需要结合实际业务场景与算法复杂度挑选出合适的算法
         */
        for(Couple husband: husbands) {
            for(Couple wife: wives) {
                // 记录循环次数
                count++;
                if(husband.getFamilyId().equals(wife.getFamilyId())) {
                    System.out.println(husband.getUserName() + "爱" + wife.getUserName());
                    // 牵手成功，把女嘉宾请下来
                    wives.remove(wife);
                    break;
                }
            }
        }
    }

    public void matchAndLog4( List<Couple> husbands, List<Couple> wives, int count ) {
        /**
         * 优化，复杂度一致的算法，无论男嘉宾的出场顺序如何改变，效率始终如一
         * 这是一种怎么样的算法呢？先思考一个问题：我们为什么要用for遍历
         * 咋一听，好像有点莫名其妙，不用for循环，我们怎么遍历
         * 使用for循环时意味着我们潜意识里已经把数据泛化，牺牲数据的特性转而谋求统一的操作方式
         * 绝大多数情况下，for循环意味着抽取共同特性，忽略个体差异，好处是代码通用，坏处是无法发挥个体优势，最终影响效率
         * 让数据产生差异化，外部通过差异化快速定位目标数据
         */

        // 给女嘉宾发牌子
        Map<Integer, Couple> wivesMap =  new HashMap<>();
        for(Couple wife: wives) {
            // 女嘉宾现在不在List里了，而是去了wivesMap中，前面放了块牌子：男嘉宾的号码
            wivesMap.put(wife.getFamilyId(), wife);
            count++;
        }

        // 男加冰上场
        for(Couple husband: husbands) {
            // 找到举着自己号码牌的女嘉宾
            Couple wife = wivesMap.get(husband.getFamilyId());
            System.out.println(husband.getUserName() + "爱" + wife.getUserName());
            count++;
        }
    }

    /**
     * 第三版和第四版的区别在于，
     * 第四版利用HashMap给其中一列数据增加了"索引"，每个数据的"索引"（Map中的key）是不同的，让数据差异化
     * 提升搜索匹配效率的两步
     * 1。 先把其中一列数据友线性结构的List转化为Hash散列的Map，为这一列数据创建"索引"，让数据差异化
     * 2。 遍历另一列数据，依据索引从Map中匹配数据
     * 相比第三版在原有的List基础上操作数据，第四版需要额外引入一个Map，内存开销稍微多了一点点
     */

}


class Couple {
    public Couple(Integer familyId, String userName) {
        this.familyId = familyId;
        this.userName = userName;
    }

    private Integer familyId;
    private String userName;

    public Integer getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Integer familyId) {
        this.familyId = familyId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
