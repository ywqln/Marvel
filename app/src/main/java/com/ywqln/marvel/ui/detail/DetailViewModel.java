package com.ywqln.marvel.ui.detail;

import com.ywqln.marvel.dto.resp.HeroRespDo;

/**
 * 描述：详情页的ViewModel实现，内部可包含数据请求
 * <p>
 *
 * @author yanwenqiang
 * @date 2019/1/13
 */
public class DetailViewModel implements IDetailViewModel {
    private HeroRespDo mHero;

    public DetailViewModel() {
        mHero = new HeroRespDo().setName("钢铁侠").setGender("男").setAge(45).setPower("有钱");
    }

    @Override
    public String getName() {
        return mHero.getName();
    }

    @Override
    public String getDetail() {
        return "姓名：" + mHero.getName() +
                "\n性别：" + mHero.getGender() +
                "\n年龄：" + mHero.getAge() +
                "\n能力：" + mHero.getPower();
    }

    public void updateHero() {
        mHero = new HeroRespDo().setName("黑寡妇").setGender("女").setAge(38).setPower("漂亮");
    }
}
