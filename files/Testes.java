//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import java.util.*;
//
//public class Testes {
//
//    @Test
//    public void testInitialization() {
//        List<Ponto> vertices = new ArrayList<>();
//        vertices.add(new Ponto(0, 0));
//        Transform transform = new Transform(5.0, 6.0, 3, 60.0, 2.0, new ArrayList<>(vertices));
//
//        assertEquals(new Ponto(5.0, 6.0), transform.position());
//        assertEquals(3, transform.layer());
//        assertEquals(60.0, transform.angle(), 1e-9);
//        assertEquals(2.0, transform.scale(), 1e-9);
//    }
//
//    @Test
//    public void testMove() {
//        Transform transform = new Transform(2.0, 3.0, 2, 10.0, 1.0, new ArrayList<>());
//        transform.move(new Ponto(3.0, -2.0), 2);
//
//        assertEquals(new Ponto(5.0, 1.0), transform.position());
//        assertEquals(4, transform.layer());
//    }
//
//    @Test
//    public void testRotate() {
//        Transform transform = new Transform(1.0, 1.0, 1, 45.0, 1.5, new ArrayList<>());
//        transform.rotate(30.5);
//        assertEquals(75.5, transform.angle(), 1e-9);
//    }
//
//    @Test
//    public void testScaleAdditive() {
//        Transform transform = new Transform(2.0, 2.0, 1, 0.0, 3.0, new ArrayList<>());
//        transform.scale(1.0);
//        assertEquals(4.0, transform.scale(), 1e-9);
//    }
//
//    @Test
//    public void testCirculoColideCirculo_Colisao() {
//        Circulo c1 = new Circulo(new Ponto(0, 0), 1.5);
//        Circulo c2 = new Circulo(new Ponto(1, 1), 1.5);
//
//        assertTrue(Colisoes.circuloColideCirculo(c1, c2));
//    }
//
//}
