import java.util.*;

class Memento
{
    private String estado;

    public Memento(String estadoParaSalvar)
    {
        estado= estadoParaSalvar;
    }

    public String getEstadoSalvado()
    {
        return estado;
    }
}

class Originator
{
    private String estado;

    public void set(String estado)
    {
        System.out.println("Originator: Situando Estado a "+estado);
        this.estado= estado;
    }

    public Memento salvandoParaMemento()
    {
        System.out.println("Originator: Salvando a Memento.");
        return new Memento(estado);
    }

    public void recuperandoDesdeMemento(Memento m)
    {
        estado = m.getEstadoSalvado();
        System.out.println("Originator: Estado despues de restaurar desde Memento: "+estado);
    }
}

class Caretaker {
    private ArrayList<Memento> estadosSalvados = new ArrayList<Memento>();

    public void addMemento(Memento m) { estadosSalvados.add(m); }
    public Memento getMemento(int index) { return estadosSalvados.get(index); }
}

class MementoEjemplo {
    public static void main(String[] args) {
        Caretaker caretaker = new Caretaker();

        Originator originator = new Originator();
        originator.set("Estado1");
        originator.set("Estado2");
        caretaker.addMemento( originator.salvandoParaMemento() );
        originator.set("Estado3");
        caretaker.addMemento( originator.salvandoParaMemento() );
        originator.set("Estado4");

        originator.recuperandoDesdeMemento( caretaker.getMemento(0) );

        //Salida
        //Originator: Situando Estado a Estado1
        //Originator: Situando Estado a Estado2
        //Originator: Salvando a Memento.
        //Originator: Situando Estado a Estado3
        //Originator: Salvando a Memento.
        //Originator: Situando Estado a Estado4
        //Originator: Estado despues de restaurar desde Memento: Estado2
    }
}