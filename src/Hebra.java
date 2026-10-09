public static class Hebra extends Thread {

    private VariableCompartida variable;

    public Hebra(VariableCompartida variable) {
        this.variable = variable;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            variable.inc();
        }
    }
}

    public static void main(String[] args) {

        VariableCompartida vc = new VariableCompartida();

        Hebra h1 = new Hebra(vc);
        Hebra h2 = new Hebra(vc);

        h1.start();
        h2.start();

        try {
            h1.join();
            h2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Valor final: " + vc.get());
    }


/* Al ejecutar dos hebras que comparten una misma variable,
inicialmente puede obtenerse el resultado esperado.
Sin embargo, al aumentar el número de incrementos aparecen valores incorrectos debido a una condición de carrera,
ya que varias hebras modifican simultáneamente la misma variable.
La solución consiste en utilizar mecanismos de sincronización como synchronized,
garantizando que solo una hebra acceda al recurso compartido cada vez.
 */