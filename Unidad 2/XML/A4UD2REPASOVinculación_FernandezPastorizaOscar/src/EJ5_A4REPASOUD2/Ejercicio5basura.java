package EJ5_A4REPASOUD2;

import java.util.ArrayList;

public class Ejercicio5basura {
    public static void main(String[] args) {
        Pesca pesca = new Pesca();

        ArrayList<Especie> especies = new ArrayList<>();

        ArrayList<String> notasRobaliza = new ArrayList<>();
        notasRobaliza.add("Puede llegar a penetrar en aguas dulces");
        Especie robaliza = new Especie("Robaliaz", "alto", "Mar", "Dicentrarchius labrax", "Lubina", new CapturaMinima("cm", 36.0), notasRobaliza);
        especies.add(robaliza);

        ArrayList<String> notasSargo = new ArrayList<>();
        notasSargo.add("Os exemplares novos viven en bancos, volvéndose solitarias ó facerse maiores");
        Especie sargo = new Especie("Sargo", "alto", "Mar", "Diplodus sargus sargus", null, new CapturaMinima("cm", 15.0), notasSargo);
        especies.add(sargo);

        ArrayList<String> notasCongrio = new ArrayList<>();
        notasCongrio.add("A parte final do seu corpo non se come, pois ten moitas espiñas");
        notasCongrio.add("Sempre sae a comer pola noite, e polo día agóichase en gretas e buracos");
        Especie congrio = new Especie("Congrio", "baixo", "Mar", "Conger conger", null, new CapturaMinima("cm", 58.0), notasCongrio);
        especies.add(congrio);

        ArrayList<String> notasPinto = new ArrayList<>();
        notasPinto.add("Son hermafrotidtas proterogínicos. Comenzan o seu crecemento como femias para transformarse posteriormente en machos");
        notasPinto.add("Presenta unha amplia gama cromática, en xeral adaptada o seu entorno");
        Especie pinto = new Especie("Pinto", "medio", "Mar", "Labrus bergylta", null, new CapturaMinima("cm", 35.0), notasPinto);
        especies.add(pinto);

        ArrayList<String> notasPolbo = new ArrayList<>();
        notasPolbo.add("As especies mais grandes poden chegar a 6 metros e 70 kgs.");
        notasPolbo.add("O terceiro brazo dereito do macho é realmente un órgano copulador");
        notasPolbo.add("Son moluscos");
        notasPolbo.add("Considéranse os invertebrados de maior intelixencia");
        Especie polbo = new Especie("Polbo", "alto", "Mar", "Octopoda", "Pulpo", new CapturaMinima("kg", 1.0), notasPolbo);
        especies.add(polbo);

        ArrayList<String> notasChoco = new ArrayList<>();
        notasChoco.add("Cando se ven ameazados soltan un chorro de tinta");
        Especie choco = new Especie("Choco", "medio", "Mar", "Sepiida", "Sepia", new CapturaMinima("cm", 20.0), notasChoco);
        especies.add(choco);

        Especie troita = new Especie("Troita", null, "Río", "Salmoninae", "Trucha", new CapturaMinima("cm", 21.0), null);
        especies.add(troita);

        ArrayList<Xornada> xornadas = new ArrayList<>();

        Xornada xornada1 = new Xornada("Cabicastro", "2020-05-21", null, null);
        xornadas.add(xornada1);

        ArrayList<Captura> capturasXornada2 = new ArrayList<>();
        capturasXornada2.add(new Captura(3, 5, pinto));
        Xornada xornada2 = new Xornada("Lagos", "2020-05-27", capturasXornada2, "A auga estaba fría e turbia. A visibilidade era de 2 metros");
        xornadas.add(xornada2);

        ArrayList<Captura> capturasXornada3 = new ArrayList<>();
        capturasXornada3.add(new Captura(1, 7.5, congrio));
        capturasXornada3.add(new Captura(5, 2.3, sargo));
        capturasXornada3.add(new Captura(1, 1.2, robaliza));
        Xornada xornada3 = new Xornada("Illa do santo", "2020-07-07", capturasXornada3, "Un día moi agradable para a pesca con boa visibilidade. A auga estaba especialmente quente.");
        xornadas.add(xornada3);

        ArrayList<Captura> capturasXornada4 = new ArrayList<>();
        capturasXornada4.add(new Captura(2, 4.8, polbo));
        Xornada xornada4 = new Xornada("Con negro", "2020-08-30", capturasXornada4, null);
        xornadas.add(xornada4);

        Xornada xornada5 = new Xornada("Area de Bon", "2020-09-03", null, "Aínda que o mar esta en calma, non se vía nada.");
        xornadas.add(xornada5);

        pesca.setEspecies(especies);
        pesca.setXornadas(xornadas);
    }
}
