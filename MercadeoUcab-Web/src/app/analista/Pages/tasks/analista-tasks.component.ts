import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { EstudioService } from '@core/services/estudio/estudio.service';
import { MatTableDataSource } from '@angular/material/table';
import { Estudio } from '@models/estudio';
import { Parroquia } from '@core/models/parroquia';
import { Municipio } from '@core/models/municipio';
import { Estado } from '@core/models/estado';
import { Pais } from '@core/models/pais';
import { Usuario } from '@core/models/usuario';
import { Solicitud } from '@core/models/solicitud';
import { UtilService } from '@core/services/utils/util.service';
import { BasicInfoDialogComponent } from '../../components/dialogs/basic-info-dialog/basic-info-dialog.component';

@Component({
  selector: 'app-analista-tasks',
  templateUrl: './analista-tasks.component.html',
  styleUrls: ['./analista-tasks.component.css'],
})
export class AnalistaTasksComponent implements OnInit {
  displayedColumns: string[] = ['id', 'expect','t_est','estado'];
  columnsToDisplay: string[] = this.displayedColumns.slice();
  estudios: any[] = [];
  dataSource: MatTableDataSource<Estudio>;
  analistaId: number = parseInt(localStorage.getItem('_id'));
  analistaUser: any = JSON.parse(localStorage.getItem('user_data'));
  searchState: string = 'U';
  constructor(
    private router: Router,
    private _utilService: UtilService,
    private _estudioService: EstudioService
  ) {}
  @ViewChild('info') private infoComponent: BasicInfoDialogComponent;
  async openInfoModal() {
    return await this.infoComponent.open();
  }
  //DATA DUMMY
  testPais: Pais = {
    _id: 1,
    nombre: 'Test pais',
  };
  testEstado: Estado = {
    _id: 1,
    nombre: 'Test estado',
    pais: this.testPais,
  };
  testMunicipio: Municipio = {
    _id: 1,
    nombre: 'Test  municipio',
    estado: this.testEstado,
  };
  testParroquia: Parroquia = {
    _id: 1,
    nombre: 'Test  parrroquia',
    municipio: this.testMunicipio,
    valorSocioEconomico: 8000,
  };
  testUser: Usuario = {
    _id: Math.floor(Math.random() * (1000 - 1) + 1),
    nombre: Math.random().toString(36).substr(2, 5),
    apellido: Math.random().toString(36).substr(2, 5),
    rol: 'Analista',
    correo: Math.random().toString(36).substr(2, 5),
    estado: 'Activo',
  };
  testSolicitud: Solicitud = {
    _id: 13,
    estado: 'activo',
    usuario: this.testUser,
    muestraPoblacion:null,
    comentarios:null,
    presentaciones:[{ _id: 1, fk_tipo: {_id:1,nombre:'test tipo',subCategoria:{
      _id: 1,
      nombre: 'test SubCategoria',
      categoria: {
         _id: 1, 
         nombre: 'test Categoria',
        },
      },
    }, 
    Cantidad: '800ml',
    tipo:'tipo st'},
  ],
  }
  /*
  {
   "status":200,
   "data":[
      {
         "_id":1,
         "estado":"En ejecucion",
         "tipo":"En linea",
         "encuestas_esperadas":1,
         "solicitud":{
            "_id":1,
            "estado":"solicitada",
            "usuario":{
               "_id":31,
               "nombre":"Caesar",
               "apellido":"Mosley",
               "rol":"cliente",
               "estado":"activo",
               "correo":"CM10@gmail.com"
            },
            "marca":"Sin especificar",
            "comentarios":"Sin comentarios",
            "presentaciones":[
               {
                  "_id":1,
                  "tipo":"Madera",
                  "Cantidad":"30x50",
                  "fk_tipo":{
                     "_id":1,
                     "nombre":"Camas",
                     "subCategoria":{
                        "_id":1,
                        "nombre":"Dormitorios",
                        "categoria":{
                           "_id":1,
                           "nombre":"Muebles"
                        }
                     }
                  }
               }
            ],
            "muestraPoblacion":{
               "_id":1,
               "genero":"masculino",
               "nivel_economico":"Alto",
               "nivel_academico":"Licenciado",
               "rango_edad_inicio":"1940-01-01",
               "rango_edad_fin":"2015-01-01",
               "cantidad_hijos":1,
               "parroquia":{
                  "_id":1,
                  "nombre":"San Camilo",
                  "valorSocioEconomico":1,
                  "municipio":{
                     "_id":1,
                     "nombre":"Manaos",
                     "estado":{
                        "_id":1,
                        "nombre":"Amazonas",
                        "pais":{
                           "_id":1,
                           "nombre":"Venezuela"
                        }
                     }
                  }
               }
            }
         },
         "analista":{
            "_id":36,
            "nombre":"Harper",
            "apellido":"Vance",
            "rol":"analista",
            "estado":"activo",
            "correo":"Harper20@gmail.com"
         },
         "encuesta":[
            {
               "_id":1,
               "pregunta":{
                  "_id":1,
                  "nombre":"Que opina del producto? ",
                  "tipo":"abierta",
                  "usuario":{
                     "_id":26,
                     "nombre":"Macon",
                     "apellido":"Mcleod",
                     "rol":"administrador",
                     "estado":"activo",
                     "correo":"MM10@gmail.com"
                  }
               },
               "respuestas":[
                  
               ]
            },
            {
               "_id":2,
               "pregunta":{
                  "_id":2,
                  "nombre":"Cuentenos, tuvo algun problema con el producto?",
                  "tipo":"abierta",
                  "usuario":{
                     "_id":26,
                     "nombre":"Macon",
                     "apellido":"Mcleod",
                     "rol":"administrador",
                     "estado":"activo",
                     "correo":"MM10@gmail.com"
                  }
               },
               "respuestas":[
                  
               ]
            },
            {
               "_id":3,
               "pregunta":{
                  "_id":3,
                  "nombre":"Como fue su experiencia con el producto?",
                  "tipo":"abierta",
                  "usuario":{
                     "_id":26,
                     "nombre":"Macon",
                     "apellido":"Mcleod",
                     "rol":"administrador",
                     "estado":"activo",
                     "correo":"MM10@gmail.com"
                  }
               },
               "respuestas":[
                  
               ]
            }
         ]
      },
      {
         "_id":2,
         "estado":"En ejecucion",
         "tipo":"En linea",
         "encuestas_esperadas":1,
         "solicitud":{
            "_id":2,
            "estado":"solicitada",
            "usuario":{
               "_id":32,
               "nombre":"Brent",
               "apellido":"Luna",
               "rol":"cliente",
               "estado":"activo",
               "correo":"Bluna@gmail.com"
            },
            "marca":"Sin especificar",
            "comentarios":"Sin comentarios",
            "presentaciones":[
               {
                  "_id":2,
                  "tipo":"Peso",
                  "Cantidad":"5 g",
                  "fk_tipo":{
                     "_id":2,
                     "nombre":"Polvo",
                     "subCategoria":{
                        "_id":2,
                        "nombre":"Profesional",
                        "categoria":{
                           "_id":2,
                           "nombre":"Maquillaje"
                        }
                     }
                  }
               }
            ],
            "muestraPoblacion":{
               "_id":2,
               "genero":"masculino",
               "nivel_economico":"Alto",
               "nivel_academico":"Licenciado",
               "rango_edad_inicio":"1940-01-01",
               "rango_edad_fin":"2015-01-01",
               "cantidad_hijos":1,
               "parroquia":{
                  "_id":1,
                  "nombre":"San Camilo",
                  "valorSocioEconomico":1,
                  "municipio":{
                     "_id":1,
                     "nombre":"Manaos",
                     "estado":{
                        "_id":1,
                        "nombre":"Amazonas",
                        "pais":{
                           "_id":1,
                           "nombre":"Venezuela"
                        }
                     }
                  }
               }
            }
         },
         "analista":{
            "_id":37,
            "nombre":"Harrison",
            "apellido":"Dorsey",
            "rol":"analista",
            "estado":"activo",
            "correo":"HARRI@gmail.com"
         },
         "encuesta":[
            {
               "_id":4,
               "pregunta":{
                  "_id":4,
                  "nombre":"Como se entero del producto?",
                  "tipo":"simple",
                  "usuario":{
                     "_id":27,
                     "nombre":"Warren",
                     "apellido":"Torres",
                     "rol":"administrador",
                     "estado":"activo",
                     "correo":"WARREN@gmail.com"
                  },
                  "opciones":[
                     {
                        "_id":1,
                        "nombre":"redes sociales"
                     },
                     {
                        "_id":2,
                        "nombre":"radio"
                     },
                     {
                        "_id":3,
                        "nombre":"TV"
                     },
                     {
                        "_id":4,
                        "nombre":"conocidos"
                     }
                  ]
               },
               "respuestas":[
                  
               ]
            },
            {
               "_id":5,
               "pregunta":{
                  "_id":5,
                  "nombre":"cuanto uso el producto?",
                  "tipo":"simple",
                  "usuario":{
                     "_id":28,
                     "nombre":"Jonas",
                     "apellido":"Mccray",
                     "rol":"administrador",
                     "estado":"activo",
                     "correo":"JON_M@gmail.com"
                  },
                  "opciones":[
                     {
                        "_id":5,
                        "nombre":"Mucho"
                     },
                     {
                        "_id":6,
                        "nombre":"Poco"
                     },
                     {
                        "_id":7,
                        "nombre":"Nada"
                     }
                  ]
               },
               "respuestas":[
                  
               ]
            },
            {
               "_id":6,
               "pregunta":{
                  "_id":7,
                  "nombre":"Recomendaria el producto?",
                  "tipo":"boolean",
                  "usuario":{
                     "_id":29,
                     "nombre":"Barclay",
                     "apellido":"Holt",
                     "rol":"administrador",
                     "estado":"activo",
                     "correo":"HOLT10@gmail.com"
                  }
               },
               "respuestas":[
                  
               ]
            }
         ]
      },
      {
         "_id":3,
         "estado":"En ejecucion",
         "tipo":"En linea",
         "encuestas_esperadas":1,
         "solicitud":{
            "_id":3,
            "estado":"solicitada",
            "usuario":{
               "_id":33,
               "nombre":"Victor",
               "apellido":"Paul",
               "rol":"cliente",
               "estado":"activo",
               "correo":"V1@gmail.com"
            },
            "marca":"Sin especificar",
            "comentarios":"Sin comentarios",
            "presentaciones":[
               {
                  "_id":3,
                  "tipo":"n/a",
                  "Cantidad":"Cientificas",
                  "fk_tipo":{
                     "_id":3,
                     "nombre":"Calculadoras",
                     "subCategoria":{
                        "_id":3,
                        "nombre":"Oficina",
                        "categoria":{
                           "_id":3,
                           "nombre":"Electronicos"
                        }
                     }
                  }
               }
            ],
            "muestraPoblacion":{
               "_id":3,
               "genero":"masculino",
               "nivel_economico":"Alto",
               "nivel_academico":"Magister",
               "rango_edad_inicio":"1940-01-01",
               "rango_edad_fin":"2015-01-01",
               "cantidad_hijos":1,
               "parroquia":{
                  "_id":1,
                  "nombre":"San Camilo",
                  "valorSocioEconomico":1,
                  "municipio":{
                     "_id":1,
                     "nombre":"Manaos",
                     "estado":{
                        "_id":1,
                        "nombre":"Amazonas",
                        "pais":{
                           "_id":1,
                           "nombre":"Venezuela"
                        }
                     }
                  }
               }
            }
         },
         "analista":{
            "_id":38,
            "nombre":"Nehru",
            "apellido":"Winters",
            "rol":"analista",
            "estado":"activo",
            "correo":"NEHR@gmail.com"
         },
         "encuesta":[
            {
               "_id":7,
               "pregunta":{
                  "_id":8,
                  "nombre":"Le gusto el producto?",
                  "tipo":"boolean",
                  "usuario":{
                     "_id":29,
                     "nombre":"Barclay",
                     "apellido":"Holt",
                     "rol":"administrador",
                     "estado":"activo",
                     "correo":"HOLT10@gmail.com"
                  }
               },
               "respuestas":[
                  
               ]
            }
         ]
      },
      {
         "_id":4,
         "estado":"En ejecucion",
         "tipo":"En linea",
         "encuestas_esperadas":1,
         "solicitud":{
            "_id":4,
            "estado":"solicitada",
            "usuario":{
               "_id":34,
               "nombre":"Kasper",
               "apellido":"Whitaker",
               "rol":"cliente",
               "estado":"activo",
               "correo":"KW@gmail.com"
            },
            "marca":"Sin especificar",
            "comentarios":"Sin comentarios",
            "presentaciones":[
               {
                  "_id":4,
                  "tipo":"Caja",
                  "Cantidad":"24 unidades",
                  "fk_tipo":{
                     "_id":4,
                     "nombre":"Lapices",
                     "subCategoria":{
                        "_id":4,
                        "nombre":"Escolares",
                        "categoria":{
                           "_id":4,
                           "nombre":"Utiles"
                        }
                     }
                  }
               }
            ],
            "muestraPoblacion":{
               "_id":4,
               "genero":"masculino",
               "nivel_economico":"Alto",
               "nivel_academico":"Magister",
               "rango_edad_inicio":"1940-01-01",
               "rango_edad_fin":"2015-01-01",
               "cantidad_hijos":1,
               "parroquia":{
                  "_id":1,
                  "nombre":"San Camilo",
                  "valorSocioEconomico":1,
                  "municipio":{
                     "_id":1,
                     "nombre":"Manaos",
                     "estado":{
                        "_id":1,
                        "nombre":"Amazonas",
                        "pais":{
                           "_id":1,
                           "nombre":"Venezuela"
                        }
                     }
                  }
               }
            }
         },
         "analista":{
            "_id":39,
            "nombre":"Reese",
            "apellido":"Dillon",
            "rol":"analista",
            "estado":"activo",
            "correo":"R_D@gmail.com"
         },
         "encuesta":[
            {
               "_id":8,
               "pregunta":{
                  "_id":9,
                  "nombre":"Lo volveria a comprar",
                  "tipo":"boolean",
                  "usuario":{
                     "_id":29,
                     "nombre":"Barclay",
                     "apellido":"Holt",
                     "rol":"administrador",
                     "estado":"activo",
                     "correo":"HOLT10@gmail.com"
                  }
               },
               "respuestas":[
                  
               ]
            },
            {
               "_id":9,
               "pregunta":{
                  "_id":10,
                  "nombre":"Cuanta calidad le da al producto?",
                  "tipo":"rango",
                  "rango":"1&10",
                  "usuario":{
                     "_id":30,
                     "nombre":"Wyatt",
                     "apellido":"Jackson",
                     "rol":"administrador",
                     "estado":"activo",
                     "correo":"Wtt@gmail.com"
                  }
               },
               "respuestas":[
                  
               ]
            },
            {
               "_id":10,
               "pregunta":{
                  "_id":11,
                  "nombre":"Cuanto le da al acabado del producto?",
                  "tipo":"rango",
                  "rango":"1&10",
                  "usuario":{
                     "_id":30,
                     "nombre":"Wyatt",
                     "apellido":"Jackson",
                     "rol":"administrador",
                     "estado":"activo",
                     "correo":"Wtt@gmail.com"
                  }
               },
               "respuestas":[
                  
               ]
            },
            {
               "_id":11,
               "pregunta":{
                  "_id":12,
                  "nombre":"En que grado recomendaria el producto?",
                  "tipo":"rango",
                  "rango":"1&10",
                  "usuario":{
                     "_id":30,
                     "nombre":"Wyatt",
                     "apellido":"Jackson",
                     "rol":"administrador",
                     "estado":"activo",
                     "correo":"Wtt@gmail.com"
                  }
               },
               "respuestas":[
                  
               ]
            },
            {
               "_id":12,
               "pregunta":{
                  "_id":13,
                  "nombre":"Como se entero del producto?",
                  "tipo":"multiple",
                  "usuario":{
                     "_id":28,
                     "nombre":"Jonas",
                     "apellido":"Mccray",
                     "rol":"administrador",
                     "estado":"activo",
                     "correo":"JON_M@gmail.com"
                  },
                  "opciones":[
                     {
                        "_id":12,
                        "nombre":"redes sociales"
                     },
                     {
                        "_id":13,
                        "nombre":"radio"
                     },
                     {
                        "_id":14,
                        "nombre":"TV"
                     },
                     {
                        "_id":15,
                        "nombre":"conocidos"
                     }
                  ]
               },
               "respuestas":[
                  
               ]
            }
         ]
      },
      {
         "_id":5,
         "estado":"En ejecucion",
         "tipo":"En linea",
         "encuestas_esperadas":1,
         "solicitud":{
            "_id":5,
            "estado":"solicitada",
            "usuario":{
               "_id":35,
               "nombre":"Solomon",
               "apellido":"Bentley",
               "rol":"cliente",
               "estado":"activo",
               "correo":"B123@gmail.com"
            },
            "marca":"Sin especificar",
            "comentarios":"Sin comentarios",
            "presentaciones":[
               {
                  "_id":5,
                  "tipo":"Talla",
                  "Cantidad":"S",
                  "fk_tipo":{
                     "_id":5,
                     "nombre":"Sacos",
                     "subCategoria":{
                        "_id":5,
                        "nombre":"Formal",
                        "categoria":{
                           "_id":5,
                           "nombre":"Vestimenta"
                        }
                     }
                  }
               }
            ],
            "muestraPoblacion":{
               "_id":5,
               "genero":"femenino",
               "nivel_economico":"Alto",
               "nivel_academico":"Doctorado",
               "rango_edad_inicio":"1940-01-01",
               "rango_edad_fin":"2015-01-01",
               "cantidad_hijos":1,
               "parroquia":{
                  "_id":1,
                  "nombre":"San Camilo",
                  "valorSocioEconomico":1,
                  "municipio":{
                     "_id":1,
                     "nombre":"Manaos",
                     "estado":{
                        "_id":1,
                        "nombre":"Amazonas",
                        "pais":{
                           "_id":1,
                           "nombre":"Venezuela"
                        }
                     }
                  }
               }
            }
         },
         "analista":{
            "_id":40,
            "nombre":"Hammett",
            "apellido":"Schneider",
            "rol":"analista",
            "estado":"activo",
            "correo":"HMLETSCH456@gmail.com"
         },
         "encuesta":[
            {
               "_id":13,
               "pregunta":{
                  "_id":14,
                  "nombre":"a quienes lo recomendaria",
                  "tipo":"multiple",
                  "usuario":{
                     "_id":28,
                     "nombre":"Jonas",
                     "apellido":"Mccray",
                     "rol":"administrador",
                     "estado":"activo",
                     "correo":"JON_M@gmail.com"
                  },
                  "opciones":[
                     {
                        "_id":16,
                        "nombre":"amigos"
                     },
                     {
                        "_id":17,
                        "nombre":"familiares"
                     },
                     {
                        "_id":18,
                        "nombre":"pareja"
                     },
                     {
                        "_id":19,
                        "nombre":"conocidos"
                     }
                  ]
               },
               "respuestas":[
                  
               ]
            },
            {
               "_id":14,
               "pregunta":{
                  "_id":1,
                  "nombre":"Que opina del producto? ",
                  "tipo":"abierta",
                  "usuario":{
                     "_id":26,
                     "nombre":"Macon",
                     "apellido":"Mcleod",
                     "rol":"administrador",
                     "estado":"activo",
                     "correo":"MM10@gmail.com"
                  }
               },
               "respuestas":[
                  
               ]
            },
            {
               "_id":15,
               "pregunta":{
                  "_id":2,
                  "nombre":"Cuentenos, tuvo algun problema con el producto?",
                  "tipo":"abierta",
                  "usuario":{
                     "_id":26,
                     "nombre":"Macon",
                     "apellido":"Mcleod",
                     "rol":"administrador",
                     "estado":"activo",
                     "correo":"MM10@gmail.com"
                  }
               },
               "respuestas":[
                  
               ]
            }
         ]
      },
      {
         "_id":6,
         "estado":"En ejecucion",
         "tipo":"En linea",
         "encuestas_esperadas":1,
         "solicitud":{
            "_id":6,
            "estado":"solicitada",
            "usuario":{
               "_id":31,
               "nombre":"Caesar",
               "apellido":"Mosley",
               "rol":"cliente",
               "estado":"activo",
               "correo":"CM10@gmail.com"
            },
            "marca":"Sin especificar",
            "comentarios":"Sin comentarios",
            "presentaciones":[
               {
                  "_id":6,
                  "tipo":"Tapa Dura",
                  "Cantidad":"N/A",
                  "fk_tipo":{
                     "_id":6,
                     "nombre":"Fisico",
                     "subCategoria":{
                        "_id":6,
                        "nombre":"Ciencia Ficion",
                        "categoria":{
                           "_id":6,
                           "nombre":"Libros"
                        }
                     }
                  }
               }
            ],
            "muestraPoblacion":{
               "_id":6,
               "genero":"femenino",
               "nivel_economico":"Alto",
               "nivel_academico":"Doctorado",
               "rango_edad_inicio":"1940-01-01",
               "rango_edad_fin":"2015-01-01",
               "cantidad_hijos":1,
               "parroquia":{
                  "_id":1,
                  "nombre":"San Camilo",
                  "valorSocioEconomico":1,
                  "municipio":{
                     "_id":1,
                     "nombre":"Manaos",
                     "estado":{
                        "_id":1,
                        "nombre":"Amazonas",
                        "pais":{
                           "_id":1,
                           "nombre":"Venezuela"
                        }
                     }
                  }
               }
            }
         },
         "analista":{
            "_id":36,
            "nombre":"Harper",
            "apellido":"Vance",
            "rol":"analista",
            "estado":"activo",
            "correo":"Harper20@gmail.com"
         },
         "encuesta":[
            {
               "_id":16,
               "pregunta":{
                  "_id":3,
                  "nombre":"Como fue su experiencia con el producto?",
                  "tipo":"abierta",
                  "usuario":{
                     "_id":26,
                     "nombre":"Macon",
                     "apellido":"Mcleod",
                     "rol":"administrador",
                     "estado":"activo",
                     "correo":"MM10@gmail.com"
                  }
               },
               "respuestas":[
                  
               ]
            },
            {
               "_id":17,
               "pregunta":{
                  "_id":4,
                  "nombre":"Como se entero del producto?",
                  "tipo":"simple",
                  "usuario":{
                     "_id":27,
                     "nombre":"Warren",
                     "apellido":"Torres",
                     "rol":"administrador",
                     "estado":"activo",
                     "correo":"WARREN@gmail.com"
                  },
                  "opciones":[
                     {
                        "_id":1,
                        "nombre":"redes sociales"
                     },
                     {
                        "_id":2,
                        "nombre":"radio"
                     },
                     {
                        "_id":3,
                        "nombre":"TV"
                     },
                     {
                        "_id":4,
                        "nombre":"conocidos"
                     }
                  ]
               },
               "respuestas":[
                  
               ]
            },
            {
               "_id":18,
               "pregunta":{
                  "_id":5,
                  "nombre":"cuanto uso el producto?",
                  "tipo":"simple",
                  "usuario":{
                     "_id":28,
                     "nombre":"Jonas",
                     "apellido":"Mccray",
                     "rol":"administrador",
                     "estado":"activo",
                     "correo":"JON_M@gmail.com"
                  },
                  "opciones":[
                     {
                        "_id":5,
                        "nombre":"Mucho"
                     },
                     {
                        "_id":6,
                        "nombre":"Poco"
                     },
                     {
                        "_id":7,
                        "nombre":"Nada"
                     }
                  ]
               },
               "respuestas":[
                  
               ]
            }
         ]
      },
      {
         "_id":7,
         "estado":"En ejecucion",
         "tipo":"En linea",
         "encuestas_esperadas":1,
         "solicitud":{
            "_id":7,
            "estado":"solicitada",
            "usuario":{
               "_id":32,
               "nombre":"Brent",
               "apellido":"Luna",
               "rol":"cliente",
               "estado":"activo",
               "correo":"Bluna@gmail.com"
            },
            "marca":"Sin especificar",
            "comentarios":"Sin comentarios",
            "presentaciones":[
               {
                  "_id":7,
                  "tipo":"Envase",
                  "Cantidad":"200 unidades",
                  "fk_tipo":{
                     "_id":7,
                     "nombre":"Capsulas",
                     "subCategoria":{
                        "_id":7,
                        "nombre":"Vitaminas",
                        "categoria":{
                           "_id":7,
                           "nombre":"Salud"
                        }
                     }
                  }
               }
            ],
            "muestraPoblacion":{
               "_id":7,
               "genero":"femenino",
               "nivel_economico":"Alto",
               "nivel_academico":"Bachiller",
               "rango_edad_inicio":"1940-01-01",
               "rango_edad_fin":"2015-01-01",
               "cantidad_hijos":1,
               "parroquia":{
                  "_id":1,
                  "nombre":"San Camilo",
                  "valorSocioEconomico":1,
                  "municipio":{
                     "_id":1,
                     "nombre":"Manaos",
                     "estado":{
                        "_id":1,
                        "nombre":"Amazonas",
                        "pais":{
                           "_id":1,
                           "nombre":"Venezuela"
                        }
                     }
                  }
               }
            }
         },
         "analista":{
            "_id":37,
            "nombre":"Harrison",
            "apellido":"Dorsey",
            "rol":"analista",
            "estado":"activo",
            "correo":"HARRI@gmail.com"
         },
         "encuesta":[
            {
               "_id":22,
               "pregunta":{
                  "_id":9,
                  "nombre":"Lo volveria a comprar",
                  "tipo":"boolean",
                  "usuario":{
                     "_id":29,
                     "nombre":"Barclay",
                     "apellido":"Holt",
                     "rol":"administrador",
                     "estado":"activo",
                     "correo":"HOLT10@gmail.com"
                  }
               },
               "respuestas":[
                  
               ]
            },
            {
               "_id":23,
               "pregunta":{
                  "_id":10,
                  "nombre":"Cuanta calidad le da al producto?",
                  "tipo":"rango",
                  "rango":"1&10",
                  "usuario":{
                     "_id":30,
                     "nombre":"Wyatt",
                     "apellido":"Jackson",
                     "rol":"administrador",
                     "estado":"activo",
                     "correo":"Wtt@gmail.com"
                  }
               },
               "respuestas":[
                  
               ]
            },
            {
               "_id":24,
               "pregunta":{
                  "_id":11,
                  "nombre":"Cuanto le da al acabado del producto?",
                  "tipo":"rango",
                  "rango":"1&10",
                  "usuario":{
                     "_id":30,
                     "nombre":"Wyatt",
                     "apellido":"Jackson",
                     "rol":"administrador",
                     "estado":"activo",
                     "correo":"Wtt@gmail.com"
                  }
               },
               "respuestas":[
                  
               ]
            }
         ]
      },
      {
         "_id":8,
         "estado":"En ejecucion",
         "tipo":"En linea",
         "encuestas_esperadas":1,
         "solicitud":{
            "_id":8,
            "estado":"solicitada",
            "usuario":{
               "_id":33,
               "nombre":"Victor",
               "apellido":"Paul",
               "rol":"cliente",
               "estado":"activo",
               "correo":"V1@gmail.com"
            },
            "marca":"Sin especificar",
            "comentarios":"Sin comentarios",
            "presentaciones":[
               {
                  "_id":8,
                  "tipo":"volumen",
                  "Cantidad":"2 l",
                  "fk_tipo":{
                     "_id":8,
                     "nombre":"Liquido",
                     "subCategoria":{
                        "_id":8,
                        "nombre":"Cloro",
                        "categoria":{
                           "_id":8,
                           "nombre":"Limpieza"
                        }
                     }
                  }
               }
            ],
            "muestraPoblacion":{
               "_id":8,
               "genero":"femenino",
               "nivel_economico":"Alto",
               "nivel_academico":"Bachiller",
               "rango_edad_inicio":"1940-01-01",
               "rango_edad_fin":"2015-01-01",
               "cantidad_hijos":1,
               "parroquia":{
                  "_id":1,
                  "nombre":"San Camilo",
                  "valorSocioEconomico":1,
                  "municipio":{
                     "_id":1,
                     "nombre":"Manaos",
                     "estado":{
                        "_id":1,
                        "nombre":"Amazonas",
                        "pais":{
                           "_id":1,
                           "nombre":"Venezuela"
                        }
                     }
                  }
               }
            }
         },
         "analista":{
            "_id":38,
            "nombre":"Nehru",
            "apellido":"Winters",
            "rol":"analista",
            "estado":"activo",
            "correo":"NEHR@gmail.com"
         },
         "encuesta":[
            {
               "_id":19,
               "pregunta":{
                  "_id":6,
                  "nombre":"Como describiria el producto?",
                  "tipo":"simple",
                  "usuario":{
                     "_id":29,
                     "nombre":"Barclay",
                     "apellido":"Holt",
                     "rol":"administrador",
                     "estado":"activo",
                     "correo":"HOLT10@gmail.com"
                  },
                  "opciones":[
                     {
                        "_id":8,
                        "nombre":"Muy util"
                     },
                     {
                        "_id":9,
                        "nombre":"Util"
                     },
                     {
                        "_id":10,
                        "nombre":"Poco util"
                     },
                     {
                        "_id":11,
                        "nombre":"Nada util"
                     }
                  ]
               },
               "respuestas":[
                  
               ]
            },
            {
               "_id":20,
               "pregunta":{
                  "_id":7,
                  "nombre":"Recomendaria el producto?",
                  "tipo":"boolean",
                  "usuario":{
                     "_id":29,
                     "nombre":"Barclay",
                     "apellido":"Holt",
                     "rol":"administrador",
                     "estado":"activo",
                     "correo":"HOLT10@gmail.com"
                  }
               },
               "respuestas":[
                  
               ]
            },
            {
               "_id":21,
               "pregunta":{
                  "_id":8,
                  "nombre":"Le gusto el producto?",
                  "tipo":"boolean",
                  "usuario":{
                     "_id":29,
                     "nombre":"Barclay",
                     "apellido":"Holt",
                     "rol":"administrador",
                     "estado":"activo",
                     "correo":"HOLT10@gmail.com"
                  }
               },
               "respuestas":[
                  
               ]
            }
         ]
      }
   ]
}
  */
  testRes = {
    status: 200,
    data: {
      _id: 2,
      estado: 'Culminado',
      tipo: 'En linea',
      encuestas_esperadas: 20,
      solicitud: {
        _id: 2,
        estado: 'solicitada',
      },
      analista: {
        _id: 37,
        nombre: 'Harrison',
        apellido: 'Dorsey',
        correo: 'HARRI@gmail.com',
        rol: 'analista',
      },
      muestra_poblacion: {
        _id: 2,
        genero: 'masculino',
        nivel_academico: 'licenciado',
        rango_edad_inicio: 15,
        rango_edad_fin: 80,
        cantidad_hijos: 1,
        parroquia: {
          _id: 1,
          nombre: 'petare',
          valorSocioEconomico: 1000,
          municipio: {
            _id: 1,
            nombre: 'Libertador',
            estado: {
              _id: 1,
              nombre: 'Guarico',
              pais: {
                _id: 1,
                nombre: 'Venezuela',
              },
            },
          },
        },
      },
      encuesta: [
        {
          _id: 4,
          pregunta: {
            _id: 4,
            nombre: 'Como se entero del producto?',
            tipo: 'simple',
            opciones: [
              {
                _id: 1,
                nombre_opcion: 'opcion 2: No la comprendo muy bien',
              },
              {
                _id: 2,
                nombre_opcion: 'radio',
              },
              {
                _id: 3,
                nombre_opcion: 'TV',
              },
              {
                _id: 4,
                nombre_opcion: 'conocidos',
              },
            ],
          },
        },
        {
          _id: 5,
          pregunta: {
            _id: 5,
            nombre: 'cuanto uso el producto?',
            tipo: 'simple',
            opciones: [
              {
                _id: 5,
                nombre_opcion: 'Mucho',
              },
              {
                _id: 6,
                nombre_opcion: 'Poco',
              },
              {
                _id: 7,
                nombre_opcion: 'Nada',
              },
            ],
          },
        },
        {
          _id: 6,
          pregunta: {
            _id: 7,
            nombre: 'Recomendaria el producto?',
            tipo: 'boolean',
          },
        },
      ],
    },
  };

  ngOnInit(): void {
    //SERVICE INVOKE
    this.invokeService();
  }
  dataFilter(dataArray: Estudio[]): Estudio[] {
    let filtered: Estudio[] = [];
    dataArray.forEach((res, ind) => {
      /*if(res._id === this.analistaId){
        filtered.push(res);
      }*/
      filtered.push(res);
    });
    console.log(dataArray, filtered);
    return filtered;
  }
  invokeService() {
    this.searchState = 'I';
    this._utilService.getEstudiosOfAnalista(this.analistaUser._id).subscribe(
      (res) => {
        //console.log(res);
        if(res.status === 200){
          this.estudios = res.data;
        }
        else {
          this.estudios = [];
        }
        this.dataSource = new MatTableDataSource<Estudio>(
          this.dataFilter(this.estudios)
        );
        this.searchState = 'D';
      },
      (err) => {
        console.log(err.message);
        this.estudios = [this.testRes['data']];
        this.dataSource = new MatTableDataSource<Estudio>(
          this.dataFilter(this.estudios)
        );
        this.searchState = 'D';
      }
    );
  }

  onDirEstudio(_route: string, Id: number): void {
    try {
      this.router.navigate([`analista/estudio/${Id}`]);
    } catch (e) {
      console.log(e.message);
    }
  }
}
