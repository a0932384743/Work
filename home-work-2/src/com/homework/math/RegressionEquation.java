package com.homework.math;

import JSci.maths.statistics.TDistribution;
import com.homework.entity.RegressionModel;
import org.apache.commons.math3.stat.descriptive.moment.Mean;

public class RegressionEquation {
    //因變量y
    private double[] dependentValues;
    //因變量yx
    private double[] independentValues;
    Mean meanUtil = new Mean();
    double xmean;
    double ymean;
    double sse;
    double sst;
    public RegressionEquation(double[] dependentValues, double[] independentValues) {
        this.dependentValues = dependentValues;
        this.independentValues = independentValues;
        xmean=meanUtil.evaluate(independentValues);
        ymean=meanUtil.evaluate(dependentValues);
    }


    public RegressionModel getRegressionModel() {
        RegressionModel model=new RegressionModel();
        double[] regression = calRegression();
        double sse=0;
        double sst=0;
        for(int i=0;i<dependentValues.length;i++) {
            double yi=independentValues[i]*regression[1]+regression[0];
            double y=dependentValues[i];
            sse=sse+(y-yi)*(y-yi);
            sst=sst+(y-ymean)*(y-ymean);
        }
        double coefficientOfDetermination = (sst-sse)/sst;
        model.setB0(regression[0]);
        model.setB1(regression[1]);
        model.setR(coefficientOfDetermination);
        Ttest(sse,model);
        return model;

    }
    /***
     * 計算回歸方程
     * @return
     */
    public double[] calRegression() {
        if(dependentValues.length!=independentValues.length) {
            return null;
        }
        double numerator=0d;
        double denominator=0d;
        for(int i=0;i<dependentValues.length;i++) {
            double x=independentValues[i];
            double y=dependentValues[i];
            numerator=numerator+(x-xmean)*(y-ymean);
            denominator=denominator+(x-xmean)*(x-xmean);
        }
        double b1=numerator/denominator;
        double b0=ymean-b1*xmean;
        double[] model= {b0,b1};
        return model;
    }
    /***
     * 利用T檢驗檢驗兩個變量之間是否存在一個顯著的回歸關係
     * @return
     */
    private void Ttest(double sse,RegressionModel model) {

        //計算標準差誤差
        double s=Math.sqrt(sse/(dependentValues.length-2));
        //計算標準差
        double xx=0;
        for(int i=0;i<independentValues.length;i++) {
            double xi=independentValues[i];
            xx=xx+((xi-xmean)*(xi-xmean));
        }
        double sb=s/Math.sqrt(xx);

        //計算T值
        double t=model.getB1()/sb;
        //计算p值
        TDistribution td=new TDistribution(dependentValues.length-2);
        double cumulative = td.cumulative(t);
        double p;
        if(t>0) {
            p=(1-cumulative)*2;
        }else {
            p=cumulative*2;
        }
        model.setP(p);
    }
}
