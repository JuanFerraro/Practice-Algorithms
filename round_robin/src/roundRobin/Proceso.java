package roundRobin;
/*Clase Proceso*/
public class Proceso {
	private Boolean bandera = false;
	private String pid; 		//ID de cada objeto proceso
    private String estado;		//Estado en que se encuentra el proceso
    private int pos = 0;
    private int tiempo_llegada;	//Tiempo de llegada del proceso a la cola.
    private int tiempo_ant;
    private int primera_asig;	//Tiempo en primera asignacion de procesador
    private int tiempo_salida;	//Tiempo en que termina.
    private int tiempo_regreso;	//Cuando hace una E/S y regresa a la cola.
    private int[] vec_es;		//Vector que contiene NCPU y tiempo en cada E/S

    //Constructor de objeto proceso.
    public Proceso(String pid, int tiempo_llegada, int[] vec_es) {
        this.pid = pid;
        this.tiempo_llegada = tiempo_llegada;
        this.vec_es = vec_es;
    }
    
    public Proceso() {}

//GETTERS AND SETTERS

	public Boolean getBandera() {
		return bandera;
	}

	public void setBandera(Boolean bandera) {
		this.bandera = bandera;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public int getTiempo_llegada() {
		return tiempo_llegada;
	}

	public void setTiempo_llegada(int tiempo_llegada) {
		this.tiempo_llegada = tiempo_llegada;
	}

	public int getTiempo_ant() {
		return tiempo_ant;
	}

	public void setTiempo_ant(int tiempo_ant) {
		this.tiempo_ant = tiempo_ant;
	}

	public int getPrimera_asig() {
		return primera_asig;
	}

	public void setPrimera_asig(int primera_asig) {
		this.primera_asig = primera_asig;
	}

	public int getTiempo_salida() {
		return tiempo_salida;
	}

	public void setTiempo_salida(int tiempo_salida) {
		this.tiempo_salida = tiempo_salida;
	}

	public int getTiempo_regreso() {
		return tiempo_regreso;
	}

	public void setTiempo_regreso(int tiempo_regreso) {
		this.tiempo_regreso = tiempo_regreso;
	}

	public int[] getVec_es() {
		return vec_es;
	}

	public void setVec_es(int[] vec_es) {
		this.vec_es = vec_es;
	}
	
	public String toString() {
        return "\t\t| P" + pid + " | " + estado + " | " +tiempo_salida + "  |";
	}
}
