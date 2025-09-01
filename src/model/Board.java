package model;

import java.util.Collection;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static model.Gamestatus.*;

public class Board {

    private final  List<List<Space>> spaces;

    public Board(List<List<Space>> space) {
        this.spaces = space;
    }

    public List<List<Space>> getSpace() {
        return spaces;
    }

    public Gamestatus getStatus(){
        if(spaces.stream()
                .flatMap(Collection::stream).noneMatch(s -> !s.isFixed() && nonNull(s.getActual()))){
            return NON_STARTED;
        }

        return spaces.stream().flatMap(Collection::stream).anyMatch(s -> isNull(s.getActual())) ? INCOMPLETE : COMPLETE;
    }


    public boolean haserrors(){
        if(getStatus() == NON_STARTED){
            return false;
        }

        return spaces.stream().flatMap(Collection::stream)
                .anyMatch(s -> nonNull(s.getActual()) && s.getActual().equals(s.getExpected()));
    }

    public boolean changeValue(final int col, final int row, final int value){
        var space = spaces.get(col).get(row);
        if(space.isFixed()){
            return false;
        }

        space.setActual(value);
        return true;

    }

    //Limpar um valor com if para caso ele seja fixo, que vai retorna falso
    public boolean clearValue(final int col, final int row){
        var space = spaces.get(col).get(row);
        if(space.isFixed()){
            return false;
        }

        space.clearSpace();
        return true;
    }

    //reseta
    public void reset(){
        spaces.forEach(c -> c.forEach(Space::clearSpace));
    }

    //Usa a lógica de verificar se tem algum erro junto ao status e se tiverem ambos sem erros o jogo da como complete
    public boolean gameisFinished(){
        return !haserrors() && getStatus() == COMPLETE;
    }

}
