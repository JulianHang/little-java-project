package tech.zlia.interest.example;

/**
 * 此例子没什么作用，主要是为了说明问题所在
 * <p>结论：用尽可能简单的方法使对象进入正常状态，如果可以的话，避免调用其他方法。
 * <p>此结论可参考编程思想第四版P164页
 * @version  2019-07-20
 * @author  zlia
 */
public class PolyConstructors {
    public static void main(String[] args) {
        new RoundGlyph(5);
    }
}

class Glyph {
    void draw () {
        System.out.println("Glyph draw()");
    }
    Glyph() {
        System.out.println("Glyph() before draw()");
        draw();
        System.out.println("Glyph() after draw()");
    }
}

class RoundGlyph extends Glyph {
    private int radius = 1;
    RoundGlyph(int r) {
        radius = r;
        System.out.println("RoundGlyph.RoundGlyph(), radius = " + radius);
    }
    @Override
    void draw () {
        System.out.println("RoundGlyph.draw, radius = " + radius);
    }
}

/* 结果展示

Glyph() before draw()
RoundGlyph.draw, radius = 0
Glyph() after draw()
RoundGlyph.RoundGlyph(), radius = 5

 */
