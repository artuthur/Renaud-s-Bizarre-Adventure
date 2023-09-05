package main;
/**
 * Entity
 */
public class Entity {

    private int pv;
    private int atq;
    private int def;
    private final String NAME;
    

    /**
     * 
     * @param pv
     * @param atq
     * @param def
     * @param name
     */
    public Entity(int pv, int atq, int def, String name){
        this.atq=atq;
        this.def=def;
        this.pv=pv;
        this.NAME=name;
    }
    
    /**
     * 
     * @return
     */
    public int getPv() {
        return pv;
    }

    /**
     * 
     * @param pv
     */
    public void setPv(int pv) {
        this.pv = pv;
    }

    /**
     * 
     * @return
     */
    public int getAtq() {
        return atq;
    }

    /**
     * 
     * @param atq
     */
    public void setAtq(int atq) {
        this.atq = atq;
    }

    /**
     * 
     * @return
     */
    public int getDef() {
        return def;
    }

    /**
     * 
     * @param def
     */
    public void setDef(int def) {
        this.def = def;
    }

    /**
     * 
     * @return
     */
    public String getNAME() {
        return NAME;
    }
}