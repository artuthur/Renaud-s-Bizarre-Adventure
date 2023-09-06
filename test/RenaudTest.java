package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.donjon.Donjon;
import main.effect.Bonus;
import main.entity.Renaud;

public class RenaudTest {
    Renaud r;
    Donjon dj;
    
    @BeforeEach
    void initialisation(){
        this.dj = new Donjon();
        this.r = new Renaud(dj);
    }

    @Test
    void test(){
        assertEquals(r.getExpCurrent(),0);
    }


    @Test
    void testGiveExpTest(){
        assertEquals(r.getExpCurrent(),0 );
        assertEquals(r.getExpNeeded(),100 );
        assertEquals(r.getLevel(),1 );
        r.giveExpTest(50);
        assertEquals(r.getExpCurrent(),50 );
        r.giveExpTest(50);
        assertEquals(r.getExpCurrent(),0 );
        assertEquals(r.getLevel(),2 );
        assertEquals(r.getExpNeeded(),120 );
    }

    @Test
    void testNextRoomTest(){
        assertEquals(this.r.getRoom(),0);
        this.r.nextRoomTest();
        assertEquals(this.r.getRoom(),1);
        this.r.nextRoomTest();
        assertEquals(this.r.getRoom(),2);
        this.r.nextRoomTest();
        assertEquals(this.r.getRoom(),3);
        this.r.nextRoomTest();
        assertEquals(this.r.getRoom(),4);
        this.r.nextRoomTest();
        assertEquals(this.r.getRoom(),0);
        assertEquals(this.r.getStage(),1);
    }

    @Test
    void testAddBonusToRenaud(){
        assertEquals(this.r.getBonusList().size(),1);
        assertEquals(this.r.getLearnedSpells().size(), 1);

        this.r.addBonusToRenaud(Bonus.BALAYETTE);
        assertEquals(this.r.getBonusList().size(), 2);
        assertTrue(this.r.getBonusList().contains(Bonus.BALAYETTE));
        assertFalse(this.r.getBonusDrawList().contains(Bonus.BALAYETTE));
        assertTrue(this.r.getLearnedSpells().contains(Bonus.BALAYETTE));

        this.r.addBonusToRenaud(Bonus.VIOLENCE);
        assertEquals(this.r.getBonusList().size(), 3);
        assertTrue(this.r.getBonusList().contains(Bonus.VIOLENCE));
    }
}
