package hotciv.standard.unitTests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import thirdparty.ThirdPartyFractalGenerator;
import static org.junit.jupiter.api.Assertions.*;

class TestFractalUnit {
    private ThirdPartyFractalGenerator fractalGenerator;

    @Test
    void firstTilesAreNotTheSame(){
        assertFalse(tilesAreTheSameIn25Games());
    }

    private boolean tilesAreTheSameIn25Games(){
        char tile = '0';
        for(int i = 0; i < 25; i++){
            fractalGenerator = new ThirdPartyFractalGenerator();
            char newTile = fractalGenerator.getLandscapeAt(0,0);

            if(tile == '0'){
                tile = fractalGenerator.getLandscapeAt(0,0);
            } else if(tile != newTile){
                return false;
            }
        }
        return true;
    }

}