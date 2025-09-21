import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MazeSolverTest {

    @Test
    void testMazeSolver() {
        MazeSolver t1 = new MazeSolver("file3.in");
        t1.output("file3.out",t1.solve());
        ArrayList<String> expected = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("file3.out"))){
            String line = null;
            while((line = br.readLine())!=null){
                expected.add(line);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ArrayList<String> expected2 = new ArrayList<>();
        try(BufferedReader br2 = new BufferedReader(new FileReader("efile3.out"))){

            String line = null;
            while((line = br2.readLine())!=null){
                expected2.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(expected2,expected);;

    }
}
