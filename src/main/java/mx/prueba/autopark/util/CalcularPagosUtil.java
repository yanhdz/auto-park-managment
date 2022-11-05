package mx.prueba.autopark.util;

import mx.prueba.autopark.domain.Estancia;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CalcularPagosUtil {

    public static Double calcularPago(Date fechaInicial,Date fechaFinal,Double price){
        Date startDate = fechaInicial;
        Date endDate   = fechaFinal;
        long duration  = endDate.getTime() - startDate.getTime();
        long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
        return diffInMinutes*price;
    }


    public static Double calcularPagoMensual(List<Estancia> estanciaList,Double price){
        Double total=0.0;
        Date startDate;
        Date endDate;
        Double pricewithDescount=0.0;
        int countDiscount=0;
        for(Estancia dato:estanciaList){
            countDiscount++;
            startDate=dato.getFechaCreada();
            endDate=dato.getFechaSalida();
            long duration  = endDate.getTime() - startDate.getTime();
            long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
            if(countDiscount<=10){
                total=total+(diffInMinutes*price);
            }
            if(countDiscount>=11){
                pricewithDescount=price*.20;
                total=total+(diffInMinutes*pricewithDescount);
            }
            if(countDiscount==20){
                total=total;
            }
            if(countDiscount>20){
                pricewithDescount=price*.20;
                total=total+(diffInMinutes*pricewithDescount);
            }
        }
        return total;
    }
}
