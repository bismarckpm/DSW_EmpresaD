export class toBackendAnswer {
  constructor(
    public dtoEncuestaEstudio: { _id: number },
    public dtousuario: { _id: number },
    public dtoopcion?: { _id: number },
    public respuesta?: string
  ) {}
}
