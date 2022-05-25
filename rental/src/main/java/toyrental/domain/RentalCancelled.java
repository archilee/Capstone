package toyrental.domain;

import toyrental.domain.*;
import toyrental.infra.AbstractEvent;
import java.util.Date;
import lombok.Data;

@Data 
public class RentalCancelled extends AbstractEvent {

    private Integer rentalId;
    private Integer customerId;
    private Integer toyId;
    private String rentalStatus;

    public RentalCancelled(){
        super();
    }
}
