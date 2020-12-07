export class Muestra_Poblacion{
    constructor(
        public id_muestra:number,
        public genero:string,
        public nivel_economico:string,
        //Posible error
        public nivel_academico:string,
        public rango_edad_inicio:number,
        public rango_edad_fin:number,
        public cantidad_hijos:number,
        //Relacion con lugar y ocupacion
        public activo:boolean,
        public creado_el:any,
        public modificado_el:any
    ){}
}