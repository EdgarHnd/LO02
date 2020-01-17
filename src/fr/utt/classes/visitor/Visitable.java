package fr.utt.classes.visitor;

/**
 * Visitable interface that declare the acceptVisit() method, in order to accept any visit.
 */
public interface Visitable {
    /**
     * Accepts any visitor to visit it.
     *
     * @param v Any visitor
     */
    void acceptVisitor(Visitor v);

}
