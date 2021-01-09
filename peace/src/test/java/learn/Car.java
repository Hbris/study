package learn;

import jakarta.validation.Constraint;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.Default;
import lombok.Data;




/**
 * Company: waiqin365
 * Description:
 * author:jjw
 * create 2021-01-06 16:40
 */
@Data
public class Car {

    @NotNull(groups = {QueryId.class})
    private String manufacturer;

    @NotNull(groups = {QueryId.class, Qublic.class})
    @Size(min = 2, max = 14,groups = {QueryId.class})
    private String licensePlate;

    @Min(value = 2)
    private int seatCount;

    interface QueryId{};
    interface Qublic extends Default {};
}
