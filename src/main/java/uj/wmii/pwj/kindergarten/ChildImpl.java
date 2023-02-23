package uj.wmii.pwj.kindergarten;

import java.util.concurrent.atomic.AtomicBoolean;

public final class ChildImpl extends Child {

    private AtomicBoolean isEating = new AtomicBoolean(false);

    public ChildImpl(String name, int hungerSpeedMs) {
        super(name, hungerSpeedMs);
        //this.isEating = new AtomicBoolean(false);
    }

    public void eatImpl() {
        if (this.isEating.get()==true){
            System.out.println(this.name()+" was commanded to eat, but he/she is already eating!");
            return;
        }
        else {
            this.isEating.set(true);
            this.eat();
            this.isEating.set(false);
        }
    }

    public AtomicBoolean isEating(){
        return this.isEating;
    }

}
