package EJ5_1;

import java.util.List;

public class Marea {
    private String data;
    private Long idPorto;
    private Long idPortoRef;
    private Double latitude;
    private List<ListaMarea> listaMareas;
    private Double lonxitude;
    private String nomePorto;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Long getIdPorto() {
        return idPorto;
    }

    public void setIdPorto(Long idPorto) {
        this.idPorto = idPorto;
    }

    public Long getIdPortoRef() {
        return idPortoRef;
    }

    public void setIdPortoRef(Long idPortoRef) {
        this.idPortoRef = idPortoRef;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public List<ListaMarea> getListaMareas() {
        return listaMareas;
    }

    public void setListaMareas(List<ListaMarea> listaMareas) {
        this.listaMareas = listaMareas;
    }

    public Double getLonxitude() {
        return lonxitude;
    }

    public void setLonxitude(Double lonxitude) {
        this.lonxitude = lonxitude;
    }

    public String getNomePorto() {
        return nomePorto;
    }

    public void setNomePorto(String nomePorto) {
        this.nomePorto = nomePorto;
    }

}
