package com.bancolombia.prubea.util;

import org.springframework.stereotype.Component;

@Component
public class Constants {

	// --------------- StatusCode -------------------------
	public static final int SUCCESS_STATUS_CODE = 200;
	public static final int BAD_REQUEST_STATUS_CODE = 400;
	public static final int UNAUTHORIZED_STATUS_CODE = 401;
	public static final int FORBIDDEN_STATUS_CODE = 403;
	public static final int NOT_FOUND_STATUS_CODE = 404;
	public static final int CONFLICT_WITH_CURRENT_STATE = 409;
	public static final int NO_CONTENT_STATUS_CODE = 204;
	public static final int INTERNAL_SERVER_ERROR_STATUS_CODE = 500;
	public static final String SISTEMA = "SISTEMA";
	public static final String FALLO_ENVIO_CORREO = "\n#---------FALLO ENVIO CORREO--------#\n";




	public static final float PORCENTAJE_HABILIDADES = 0.5F;

	public static final String EVALUACION_ATRACCION_BAJO = "ATRBAJ";
	public static final String EVALUACION_ATRACCION_MEDIO = "ATRMED";
	public static final String EVALUACION_ATRACCION_ALTO = "ATRALT";
	public static final String EVALUACION_ATRACCION_REGISTRO_ANULADO = "REGANU";
	public static final String EVALUACION_ATRACCION_SOLUCITUD_CANCELADA = "SOLCAN";
	public static final String EVALUACION_ATRACCION_DESISTIO_CANDIDATO = "DES";
	public static final String EVALUACION_ATRACCION_NO_REALIZO_EVALUACION = "RENOE";
	public static final String EVALUACION_FUERA_DE_VENTANA = "REFUEV";
	public static final String EVALUACION_ATRACCION_RECHAZADO_ERROR_EN_PLATAFORMA_NO_SE_HIZO_EVALUACION = "REERR";
	public static final String EVALUACION_ATRACCION_RECHAZADO_PERFIL_ENVIADO_NO_SE_AJUSTA = "RENOAJS";
	public static final String EVALUACION_ATRACCION_RECHAZADO_CANIBALISMO = "RECNB";
	public static final String EVALUACION_ATRACCION_RECHAZADO_PERFIL_ENVIADO_FUERA_DE_VENTANA = "REFUE";
	public static final String EVALUACION_ATRACCION_RECHAZADO_NO_CUMPLE_CRITERIOS_DE_CONTRATACION = "RENOCUM";
	public static final String EVALUACION_ATRACCION_PRESELECCIONADO = "PRE";

	public static final String INGRESO_ESTADO_CREADO = "CRE";
	public static final String INGRESO_ESTADO_ENPROCESO_REVISION_CONOCIMIENTOS_Y_CAPACIDAD = "PRREV";
	public static final String INGRESO_ESTADO_ENPROCESO_ANALISIS_VIABILIDAD_DE_MOVIMIENTO = "PRANA";
	public static final String INGRESO_ESTADO_ENPROCESO_PUBLICACION_SOLICITUD_A_PROVEEDORES = "PRPUB";
	public static final String INGRESO_ESTADO_ENPROCESO_SE_HAN_RECIBIDO_TALENTOS_POR_PARTE_DEL_PROVEEDOR = "PRTAL";
	public static final String INGRESO_ESTADO_ENPROCESO_APLICACION_EVALUACION_TECNICA_SERVICIOS_DIAGNOSTICO = "PRAPL";
	public static final String INGRESO_ESTADO_ENPROCESO_NOTIFICACION_AL_PROVEEDOR_DE_TALENTO_SELECCIONADO = "PRNOT";
	public static final String INGRESO_ESTADO_ENPROCESO_AISGNACION_DE_TALENTO_AL_BANCO_POR_PARTE_DEL_PROVEEDOR = "PRASG";
	public static final String INGRESO_ESTADO_CERRADA = "CER";
	public static final String INGRESO_ESTADO_SUSPENDIDO = "SUS";
	public static final String INGRESO_ESTADO_CANCELADO = "CAN";

	public static final String RETIRO_CAUSAl_REPORTADO_PROVEEDOR = "CA004";
	public static final String RETIRO_TIPO_RETIRO_INTERNALIZACION = "TR03";
	public static final String RETIRO_TIPO_RETIRO_NO_APLICA = "TR05";
	public static final String RETIRO_CAUSAl_FIN_ASIGNACION = "CA006";

	public static final String EMAIL_SKILLHACK = "SkillHackers@bancolombia.onmicrosoft.com";
	public static final String EMAIL_SKILLHACK_BCC = "SkillHackers@bancolombia.onmicrosoft.com";

	public static final String BD_CONOCIMIENTO_PREFIJO = "C";
	public static final String BD_CONOCIMIENTO_TABLA = "Conocimiento";
	public static final String BD_CONOCIMIENTO_ID = "idConocimiento";
	public static final String BD_CELULA_PREFIJO = "CE";
	public static final String BD_CELULA_TABLA = "Celula";
	public static final String BD_CELULA_ID = "idCelula";
	public static final String BD_PROYECTO_PREFIJO = "EV";
	public static final String BD_PROYECTO_TABLA = "Proyecto";
	public static final String BD_PROYECTO_ID = "idProyecto";
	public static final String BD_AUTORIZACION_PREFIJO = "AUT";
	public static final String BD_AUTORIZACION_TABLA = "Autorizacion";
	public static final String BD_AUTORIZACION_ID = "idAutorizacion";
	public static final String BD_MAPA_CONOCIMIENTO_PREFIJO = "MC";
	public static final String BD_MAPA_CONOCIMIENTO_TABLA = "MapaConocimiento";
	public static final String BD_MAPA_CONOCIMIENTO_ID = "idMapaConocimiento";
	public static final String BD_TAREA_PROGRAMADA_PREFIJO = "TP";
	public static final String BD_TAREA_PROGRAMADA_TABLA = "TareaProgramada";
	public static final String BD_TAREA_PROGRAMADA_ID = "idTareaProgramada";
	public static final String BD_NIVEL_ROL_PERSONA_PREFIJO = "NRP";
	public static final String BD_NIVEL_ROL_PERSONA_TABLA = "NivelRolPersona";
	public static final String BD_NIVEL_ROL_PERSONA_ID = "idNivelRolPersona";
	public static final String BD_PERSONA_CELULA_PREFIJO = "PCE";
	public static final String BD_PERSONA_CELULA_TABLA = "PersonaCelula";
	public static final String BD_PERSONA_CELULA_ID = "idPersonaCelula";
	public static final String BD_PERSONA_ESTRUCTURA_PREFIJO = "PE";
	public static final String BD_PERSONA_ESTRUCTURA_TABLA = "PersonaEstructura";
	public static final String BD_PERSONA_ESTRUCTURA_ID = "idPersonaEstructura";

	public static final String BD_PERSONA_GESTION_GASTO_PREFIJO = "PGG";
	public static final String BD_ESTRUCTURA_PREFIJO = "EST";
	public static final String BD_ESTRUCTURA_TABLA = "Estructura";
	public static final String BD_ESTRUCTURA_ID = "idEstructura";
	public static final String BD_POSICION_PREFIJO = "PS";
	public static final String BD_DSPOSICION_PREFIJO = "EX";
	public static final String BD_POSICION_TABLA = "Posicion";
	public static final String BD_POSICION_ID = "idPosicion";
	public static final String BD_POSICION_DS = "dsPosicion";
	public static final String BD_POSICION_ESTRUCTURA_PREFIJO = "PE";
	public static final String BD_POSICION_ESTRUCTURA_TABLA = "PosicionEstructura";
	public static final String BD_POSICION_ESTRUCTURA_ID = "idPosicionEstructura";
	public static final String BD_HISTORIA_ESTRUCTURA_PREFIJO = "HS";
	public static final String BD_HISTORIA_ESTRUCTURA_TABLA = "HistoriaEstructura";
	public static final String BD_HISTORIA_ESTRUCTURA_ID = "idHistoriaEstructura";

	public static final String BD_CARRERA_PREFIJO = "CA";
	public static final String BD_CARRERA_TABLA = "Carrera";
	public static final String BD_CARRERA_ID = "idCarrera";
	public static final String BD_CONOCIMIENTO_EXCLUIDO_PREFIJO = "COE";
	public static final String BD_CONOCIMIENTO_EXCLUIDO_TABLA = "ConocimientoExcluido";
	public static final String BD_CONOCIMIENTO_EXCLUIDO_ID = "idConocimientoExcluido";
	public static final String BD_PATH_FORMATIVO_PREFIJO = "PF";
	public static final String BD_PATH_FORMATIVO_TABLA = "PathFormativo";
	public static final String BD_PATH_FORMATIVO_ID = "idPathFormativo";
	public static final String BD_CURSO_PREFIJO = "CU";
	public static final String BD_CURSO_TABLA = "Curso";
	public static final String BD_CURSO_ID = "idCurso";
	public static final String BD_PERFIL_PROVEEDOR_PERSONA_PREFIJO = "PPP";
	public static final String BD_PERFIL_PROVEEDOR_PERSONA_TABLA = "PerfilProveedorPersona";
	public static final String BD_PERFIL_PROVEEDOR_PERSONA_ID = "idPerfilProveedorPersona";
	public static final String BD_PERSONA_EMPRESA_PREFIJO = "PEM";
	public static final String BD_PERSONA_EMPRESA_TABLA = "PersonaEmpresa";
	public static final String BD_PERSONA_EMPRESA_ID = "idPersonaEmpresa";
	public static final String BD_ROL_RECURSO_TABLA = "RolRecurso";
	public static final String BD_ROL_RECURSO_PREFIJO = "RR";
	public static final String BD_ROL_RECURSO_ID = "idRolRecurso";
	public static final String BD_ELIMINAR_USUARIO_AZURE_PREFIJO = "AD";

	public static final String BD_ELIMINAR_USUARIO_HCM_PREFIJO = "HC";

	public static final String BD_PERSONA_CURSO_PREFIJO = "PC";
	public static final String BD_PERSONA_CURSO_TABLA = "PersonaCurso";
	public static final String BD_PERSONA_CURSO_ID = "idPersonaCurso";

	public static final String BD_PERSONA_CANDIDATO_PREFIJO = "PC";
	public static final String BD_PERSONA_CANDIDATO_TABLA = "PersonaCandidato";
	public static final String BD_PERSONA_CANDIDATO_ID = "idPersonaCandidato";

	public static final String BD_OFERTA_PREFIJO = "OF";
	public static final String BD_OFERTA_TABLA = "Oferta";
	public static final String BD_OFERTA_ID = "idOferta";

	public static final String BD_CONOCIMIENTO_OFERTA_PREFIJO = "CO";

	public static final String BD_CONOCIMIENTO_OFERTA_TABLA = "ConocimientoOferta";
	public static final String BD_PERFIL_OFERTA_PREFIJO = "PFO";
	public static final String BD_PERFIL_OFERTA_TABLA = "PerfilOferta";
	public static final String BD_PERFIL_OFERTA_ID = "idPerfilOferta";
	public static final String BD_CONOCIMIENTO_OFERTA_ID = "idConocimientoOferta";

	public static final String BD_PERSONA_OFERTA_PREFIJO = "PO";
	public static final String BD_PERSONA_OFERTA_TABLA = "PersonaOferta";
	public static final String BD_PERSONA_OFERTA_ID = "idPersonaOferta";

	public static final String BD_POSTEO_PREFIJO = "PO";
	public static final String BD_POSTEO_TABLA = "Posteo";
	public static final String BD_POSTEO_ID = "idPosteo";

	public static final String BD_POSTEO_PROVEEDOR_PREFIJO = "PP";
	public static final String BD_POSTEO_PROVEEDOR_TABLA = "PosteoProveedor";
	public static final String BD_POSTEO_PROVEEDOR_ID = "idPosteoProveedor";

	public static final String BD_DIAGNOSTICO_PREFIJO = "D";
	public static final String BD_DIAGNOSTICO_TABLA = "Diagnostico";
	public static final String BD_DIAGNOSTICO_ID = "idDiagnostico";

	public static final String BD_MCQ_PREGUNTA_PREFIJO = "PRE";
	public static final String BD_MCQ_PREGUNTA_TABLA = "McqPregunta";
	public static final String BD_MCQ_PREGUNTA_ID = "idMcqPregunta";

	public static final String BD_MCQ_RESPUESTA_PREFIJO = "RES";
	public static final String BD_MCQ_RESPUESTA_TABLA = "McqRespuesta";
	public static final String BD_MCQ_RESPUESTA_ID = "idMcqRespuesta";

	public static final String BD_LINEA_CONOCIMIENTO_PREFIJO = "LC";
	public static final String BD_LINEA_CONOCIMIENTO_TABLA = "LineaConocimiento";
	public static final String BD_LINEA_CONOCIMIENTO_ID = "idLineaConocimiento";
	
	public static final String BD_ROL_PREFIJO = "R";
	public static final String BD_ROL_TABLA = "Rol";
	public static final String BD_ROL_ID = "idRol";
	public static final String BD_PERFIL_PREFIJO = "PF";
	public static final String BD_PERFIL_TABLA = "Perfil";
	public static final String BD_PERFIL_ID = "idPerfil";

	public static final String TABLA_SI = "SÍ";
	public static final String TABLA_NO = "NO";
	public static final String TABLA_NODO_RAIZ = "NODO RAÍZ";

	public static final String IDIOMA_INGLES = "EN";
	public static final String IDIOMA_ESPANIOL = "ES";

	public static final String CURSO_MODALIDAD_PRESENCIAL = "P";
	public static final String CURSO_MODALIDAD_VIRTUAL = "V";
	public static final String CURSO_MODALIDAD_VIRTUAL_D = "D";

	public static final String REPORTE_CAUSAL_MOVIMIENTO = "MOVIMIENTO";
	public static final String REPORTE_CAUSAL_MOVIMIENTO_ID = "CA010";
	public static final Integer DIAS_ESPERA_POR_ETSNO_SATISFACTORIA = 30;

	public static final Object GENERAL_ID_EMPRESA_BANCOLOMBIA = "EM5";
	public static final String ERROR = "error";
	public static final String RESPONSE = "response";
	public static final String MESSAGE = "message";
	public static final String MESSAGE_ERROR_BACKEND = "Error en la capa de backend";


	public static final String TRAININGS_REPORT_FILE_NAME = "Reporte de Formación";
	public static final String TRAININGS_REPORT_SHEET_NAME = "Formación";
	public static final String[] TRAININGS_REPORT_COLUMN_NAME = {
				"CÉDULA", "NOMBRE COMPLETO", "DIRECCIÓN ADM.", "COMPONENTE MAYOR ADM.", "COMPONENTE MENOR ADM.", "CÉLULA", "NOMBRE DEL PROGRAMA",
				"TIPO DE PROGRAMA", "MODALIDAD", "CÓD. PROGRAMA", "DESCRIPCIÓN", "RUTA DE CONOCIMIENTO", "FECHA INICIO", "FECHA FIN", "MES PAGO",
				"COSTO", "ESTADO DEL PROGRAMA", "ORIGEN DEL PRESUPUESTO", "CONOCIMIENTO A APALANCAR", "TIPO DE CONOCIMIENTO", "OBSERVACIONES"
			};
	public static final String CERTIFICATIONS_REPORT_FILE_NAME = "Reporte de Certificación";
	public static final String CERTIFICATIONS_REPORT_SHEET_NAME = "Certificación";
	public static final String[] CERTIFICATIONS_REPORT_COLUMN_NAME = {
				"CÉDULA", "NOMBRE COMPLETO", "DIRECCIÓN ADM.", "COMPONENTE MAYOR ADM.", "COMPONENTE MENOR ADM.", "CÉLULA", "NOMBRE CERTIFICACIÓN",
				"FECHA EMISIÓN", "FECHA EXPIRACIÓN", "ESTADO CERTIFICACIÓN", "CONOCIMIENTO A APALANCAR", "TIPO DE CONOCIMIENTO"
			};
	public static final String DIAGNOSTICS_REPORT_FILE_NAME = "Reporte de Diagnóstico";
	public static final String DIAGNOSTICS_REPORT_SHEET_NAME = "Diagnóstico";
	public static final String[] DIAGNOSTICS_REPORT_COLUMN_NAME = {
				"CÉDULA", "NOMBRE COMPLETO", "ROL", "DIRECCIÓN ADM.", "COMPONENTE MAYOR ADM.", "COMPONENTE MENOR ADM.", "CÉLULA", "FECHA EJECUCIÓN",
				"NIVEL OBJETIVO", "RESULTADO", "ESTADO DEL DIAGNÓSTICO", "CONOCIMIENTO A APALANCAR", "TIPO DE CONOCIMIENTO"
			};
	public static final String MENSAJE_SOPORTE_VALKO = " por favor contacte a soportevalko@bancolombia.onmicrosoft.com";
	public static final String AUTODIAGNOSTICO = "Autodiagnóstico";
	public static final String EXITOSO = "EXITOSO";
	public static final String FALLIDO = "FALLIDO";
	public static final String DIRECCION = "DIRECCIÓN";
	public static final String COMPONENTE_MAYOR = "COMPONENTE MAYOR";
	public static final String COMPONENTE_MENOR = "COMPONENTE MENOR";
	public static final String ID_POSICION = "ID POSICIÓN";
	public static final String POSICION = "POSICION";
	public static final String TIPO_POSICION = "TIPO";
	public static final String ESTADO_VACANTE = "ESTADO VACANTE";
	public static final String FECHA_VACANTE = "FECHA VACANTE";
	public static final String ANTIGUEDAD_VACANTE = "ANTIGÜEDAD DE LA VACANTE (mes)";
	public static final String MOVIMIENTO = "MOVIMIENTO";
	public static final String NO_APLICA = "NO APLICA";
	public static final String NO_EXISTE = "NO EXISTE";
	public static final Integer ZERO = 0;
	public static final Integer ONE = 1;
	public static final String STATUS_DEACTIVATED = "Deactivated";
	public static final String STATUS_ACTIVE = "Active";

	public static final String REPORTE_CONSOLIDADO_INGRESOS = "reporte consolidado ingresos";
	
	public static final Integer TOTAL_SUM_WEIGHTS = 100;

	public static final String STRATEGY_EFFICIENCY_LAST_AVAILABLE_DAY = "2022-04-30";

	public static final String XLS_FORMAT = ".xlsx";

	public static final Integer MAX_STRING_LENGTH = 4900;
	
	public static final String SURVEY_NOTIFICATION_PREFIX = "NE-";
	
	public static final String EMAIL_REGULAR_EXPRESSION = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
	
	public static final String DAY_MONTH_YEAR_DATE_FORMAT = "dd/MM/yyyy";
	
	public static final String ISO_8601 = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";


	public static final Integer A_CLOUD_GURU_MAX_ROW = 250;
	public static final String A_CLOUD_GURU_KNOWLEDGE_SOURCE_ID = "FC023";
	public static final String BASE = "BASE";
	public static final String ESPECIALIZACION = "ESPECIALIZACION";
	public static final Integer NUMBER_LANGUAGE_SKILLS = 2;
	public static final String SQL = "SQL";
	public static final String FRONTEND = "Frontend";

	public static final String REQUEST_CANCELED_BY_PROVIDER = "El servicio ha sido cancelado por indicaciones del proveedor";
	public static final Character PROVIDER_POST_ADDED = 'S';
	public static final Character PROVIDER_POST_NOT_ADDED = 'N';

	public static final String NO_PERSON_HAS_NO_POSITION_MSG = "\n Observación: \n La persona no tiene una posición ligada";
	public static final String NO_PERSON_HAS_NO_COMPANY_MSG =  "\n Observación: \n La persona no tiene una empresa activa"
			+		" ligada";

	public static final String MENSAJE_RESTRICCION_TIEMPO_INSCRIPCION_RETIRO_CURSO = "En este momento no es posible inscribirse o retirarse de la oferta, ya que la misma inicia en menos de {0} días. Por favor, contacte a Skill Hacking al correo SkillHackers@bancolombia.onmicrosoft.com para revisar opciones.";
}
