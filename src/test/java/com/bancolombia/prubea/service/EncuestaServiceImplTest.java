package com.bancolombia.prubea.service;

import com.bancolombia.prubea.dto.EncuestaDto;
import com.bancolombia.prubea.dto.PreguntaEDto;
import com.bancolombia.prubea.dto.ServiceResponseDto;
import com.bancolombia.prubea.entity.*;
import com.bancolombia.prubea.repository.EncuestaRepository;
import com.bancolombia.prubea.repository.PersonaEncuestaRespository;
import com.bancolombia.prubea.repository.PreguntaERepository;
import com.bancolombia.prubea.repository.TipoEncuestaRepository;
import com.bancolombia.prubea.util.Constants;
import cucumber.api.java.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

//@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class EncuestaServiceImplTest {
    // Inicializar la instancia del servicio que se quiere probar
    @InjectMocks
    private EncuestaServiceImpl encuestaService;

    // Crear un objeto mock del repositorio para que el servicio lo utilice
    @Mock
    private EncuestaRepository surveyRepository;
    @Mock

    private PreguntaERepository questionRepository;
    @Mock
    private TipoEncuestaRepository tipoEncuestaRepository;
    @Mock
    private PersonaEncuestaRespository personaEncuestaRespository;

    // Creacion de variables y objetos en comun
    TipoEncuesta tipoEncuesta;
    TipoPregunta tipoPregunta;
    Encuesta activeSurvey;
    Set<PreguntaE> preguntaEs;
    PreguntaE preguntaE;
    Set<Respuesta>respuestas;
    Respuesta respuesta;
    PersonaEncuesta personaEncuesta;

    // Método que se ejecuta antes de cada prueba para inicializar los objetos mockeados
    @Before
    public void setUp() {
        tipoEncuesta = TipoEncuesta.builder()
                .idTipoEncuesta("2")
                .dsTipoEncuesta("Satisfaccion")
                .build();

        tipoPregunta = TipoPregunta.builder()
                .idTipoPregunta("1")
                .dsTipoPregunta("TEXTO")
                .build();

        activeSurvey = Encuesta.builder()
                .idEncuesta("Sebas321")
                .nombreEncuesta("Prueba")
                .dsEncuesta("Descripcion")
                .cantidadPreguntas(1)
                .preguntas(preguntaEs)
                .esEncuesta("Activada")
//                .tipoEncuesta(tipoEncuesta)
                .recurso("REC005")
                .build();

        preguntaE = PreguntaE.builder()
                .idPregunta("NatiOU")
                .indexPregunta(1)
                .dsPregunta("Esta es una pregunta")
                .opciones("Muchas Opciones")
                .encuesta(activeSurvey)
//                .tipoPregunta(tipoPregunta)
                .build();
//        preguntaEs.add(preguntaE);

        preguntaEs = new HashSet<>();

        personaEncuesta = PersonaEncuesta.builder()
                .idPersonaEncuesta("1")
                .build();

        respuesta = Respuesta.builder()
                .idRespuesta("Dani123")
                .respuesta("Esta es una respuesta")
                .pregunta(preguntaE)
                .personaEncuesta(personaEncuesta)
                .build();

        respuestas = new HashSet<>();

    }

    @Test
    @DisplayName("Prueba para verificar que no existe encuestas aun en la db")
    public void testValidateActiveSatisfactionSurveyWithNoSurveysInDataBase() {
        // Simular que no hay una encuesta activada en el repositorio
        when(surveyRepository.count()).thenReturn(0L);

        // Ejecutar el servicio
        ServiceResponseDto response = encuestaService.validateActiveSatisfactionSurvey();

        // Verificar los resultados esperados
        assertEquals(Constants.SUCCESS_STATUS_CODE, response.getStatusCode());
        assertEquals("No existen encuestas aun!, puedes crear la 1ra.", response.getData().get("message"));
    }

    @Test
    @DisplayName("Prueba para verificar el comportamiento cuando no hay una encuesta activada")
    public void testValidateActiveSatisfactionSurveyWithNoActiveSurvey() {
        // Simular que no hay una encuesta activada en el repositorio
        when(surveyRepository.count()).thenReturn(1L);

        // Ejecutar el servicio
        ServiceResponseDto response = encuestaService.validateActiveSatisfactionSurvey();

        // Verificar los resultados esperados
        assertEquals(Constants.SUCCESS_STATUS_CODE, response.getStatusCode());
        assertEquals("No existe encuesta de satisfaccion activa, puedes crear la encuesta!", response.getData().get("message"));
    }

    @Test
    @DisplayName("Prueba para verificar el comportamiento cuando hay una encuesta activada")
    public void testValidateActiveSatisfactionSurveyWithActiveSurvey() {
        // Simular una encuesta activada en el repositorio
        activeSurvey.setTipoEncuesta(tipoEncuesta);
        activeSurvey.setPreguntas(preguntaEs);
        preguntaE.setTipoPregunta(tipoPregunta);
        preguntaEs.add(preguntaE);
        when(surveyRepository.count()).thenReturn(1L);
        when(surveyRepository.findByTipoEncuestaEsEncuesta("2", "Activada")).thenReturn(Optional.of(activeSurvey));

        // Ejecutar el servicio
        ServiceResponseDto response = encuestaService.validateActiveSatisfactionSurvey();

        // Verificar los resultados esperados
        assertEquals(Constants.CONFLICT_WITH_CURRENT_STATE, response.getStatusCode());
        assertNotNull(response.getData().get("objects"));
        assertEquals(1, (int) response.getData().get("total"));
        assertEquals("Ya hay una encuesta Activa de satisfaccion", response.getData().get("message"));
    }


    @Test
    @DisplayName("Prueba para verificar (Catch) comportamiento cuando se produce un error en el repositorio")
    public void testValidateActiveSatisfactionSurveyWithError() {
        // Simular un error al acceder al repositorio
        when(surveyRepository.count()).thenThrow(new RuntimeException("Simulated error"));

        // Ejecutar el servicio
        ServiceResponseDto response = encuestaService.validateActiveSatisfactionSurvey();

        // Verificar los resultados esperados
        assertEquals(Constants.INTERNAL_SERVER_ERROR_STATUS_CODE, response.getStatusCode());
        assertEquals("Error al buscar una encuesta de satisfaccion activa!", response.getData().get("message"));
    }


    @Test
    @DisplayName("Prueba que genera error al intentar crear una encuesta, debido a que no cuenta con preguntas")
    public void createSurveyWithErrorBecauseEmptyQuestions(){
        activeSurvey.setTipoEncuesta(tipoEncuesta);
        activeSurvey.setPreguntas(preguntaEs);

        ServiceResponseDto response = encuestaService
                .createSurvey(EncuestaDto.convertSurveyToSurveyDto(activeSurvey));

        assertEquals(Constants.BAD_REQUEST_STATUS_CODE, response.getStatusCode());
        assertEquals("La encuesta no cuenta con preguntas", response.getData().get("message"));
    }

    @Test
    @DisplayName("Prueba para crear un encuesta de forma exitosa")
    public void createSurveySuccessfully(){
        activeSurvey.setTipoEncuesta(tipoEncuesta);
        preguntaE.setTipoPregunta(tipoPregunta);
        preguntaEs.add(preguntaE);
        Set<PreguntaEDto> questionsDto = preguntaEs.stream()
                .map(PreguntaEDto::convertQuestionsToQuestionsDtoWithoutAnswers).collect(Collectors.toSet());
        EncuestaDto surveyDto = EncuestaDto.convertSurveyToSurveyDto(activeSurvey);
        surveyDto.setQuestionDto(questionsDto);

        ServiceResponseDto response = encuestaService.createSurvey(surveyDto);

        assertEquals(Constants.SUCCESS_STATUS_CODE, response.getStatusCode());
        assertEquals("encuesta creada con exito!", response.getData().get("message"));
    }

    @Test
    @DisplayName("Prueba para generar error del servidor al intentar crear una encuesta")
    public void createSurveyWithError(){
        ServiceResponseDto response = encuestaService.createSurvey(null);

        assertEquals(Constants.INTERNAL_SERVER_ERROR_STATUS_CODE, response.getStatusCode());
        assertEquals("Error al tratar de crear la encuesta!", response.getData().get("message"));
    }

    @Test
    @DisplayName("Prueba para verificar el comportamiento cuando no se encuentre una encuesta por su ID")
    public void testGetSurveyWithQuestionsWithCountZeroAndSurveyNull() {
        //Simulacion de busqueda en la base de datos (count = 0)
        when(surveyRepository.count()).thenReturn(0L);

        //Ejecutar el servicio
        ServiceResponseDto response = encuestaService.getSurveyWithQuestions("Sebas321");

        //Verificar los resultados esperados
        assertEquals(Constants.NOT_FOUND_STATUS_CODE, response.getStatusCode());
        assertEquals("No se encontraron encuestas!", response.getData().get("message"));
    }

    @Test
    @DisplayName("Prueba para verificar el comportamiento cuando hay encuestas en la base de datos pero no el ID buscado")
    public void testGetSurveyWithQuestionsWithCountNotZeroAndSurveyNull() {
        //Simulacion de busqueda en la base de datos (count != 0 && survey == null)
        when(surveyRepository.count()).thenReturn(1L);
        when(surveyRepository.getEncuestaForId(anyString())).thenReturn(null);

        //Ejecutar el servicio
        ServiceResponseDto response = encuestaService.getSurveyWithQuestions("Sebas123");

        //Verificar los resultados esperados
        assertEquals(Constants.NOT_FOUND_STATUS_CODE, response.getStatusCode());
        assertEquals("No se encuentra la encuesta buscada!", response.getData().get("message"));
    }

    @Test
    @DisplayName("Prueba para verificar el comportamiento cuando se encuentre una encuesta con sus preguntas a través del ID")
    public void testGetSurveyWithQuestionsWithCountNotZeroAndSurveyNotNull() {
        //Simulacion de busqueda en la base de datos (count != 0 && getEncuestaForId != null)
        Encuesta survey = activeSurvey;
        preguntaE.setTipoPregunta(tipoPregunta);
        preguntaEs.add(preguntaE);
        survey.setPreguntas(preguntaEs);
        survey.setTipoEncuesta(tipoEncuesta);
        when(surveyRepository.getEncuestaForId("Sebas321")).thenReturn(survey);
        when(surveyRepository.count()).thenReturn(1L);

        //Ejecutar el servicio
        ServiceResponseDto response = encuestaService.getSurveyWithQuestions("Sebas321");

        //Verificar los resultado esperados
        assertEquals(Constants.SUCCESS_STATUS_CODE, response.getStatusCode());
    }

    @Test
    @DisplayName("Prueba para verificar el comportamiento cuando se produce un error en el repositorio")
    public void testGetSurveyWithQuestionsWithCountError()
    {
        //Silumacion de busqueda en la base de datos (count == error)
        when(surveyRepository.count()).thenThrow(new RuntimeException("Simulated error"));

        //Ejecutar el servicio
        ServiceResponseDto response = encuestaService.getSurveyWithQuestions("Sebas321");

        //Verificar los resultados esperados
        assertEquals(Constants.INTERNAL_SERVER_ERROR_STATUS_CODE, response.getStatusCode());
        assertEquals("Error al buscar la encuesta", response.getData().get("message"));
    }

    @Test
    @DisplayName("Prueba para verificar el comportamiento cuando no hay encuestas creadas")
    public void testGetSurveyWithQuestionsAndAnswersWithCountZero(){
        //Simulacion de busqueda en la base de datos (count == 0)
        when(surveyRepository.count()).thenReturn(0L);
        //Ejecutar el servicio
        ServiceResponseDto response =  encuestaService.getSurveyWithQuestionsAndAnswers("Sebas321");
        //Verificar los resultados esperados
        assertEquals(Constants.NOT_FOUND_STATUS_CODE, response.getStatusCode());
        assertEquals("No se encontraron encuestas!", response.getData().get("message"));
    }

    @Test
    @DisplayName("Prueba que genera codigo 404 debido a que la db no tiene encuestas")
    public void testDeleteWithErrorBecauseSurveyDoesNotExist() {
        when(surveyRepository.count()).thenReturn(0L);

        ServiceResponseDto response = encuestaService.deleteSurvey("1");

        assertEquals(Constants.NOT_FOUND_STATUS_CODE, response.getStatusCode());
        assertEquals("No se encontraron encuestas!", response.getData().get("message"));
    }

    @Test
    @DisplayName("Prueba que genera codigo 404 de encuesta no encontrada, debido a que no existe en la db")
    public void testDeleteWithErrorBecauseDBHasNotSurveys(){
        when(surveyRepository.count()).thenReturn(1L);

        ServiceResponseDto response = encuestaService.deleteSurvey("1");

        assertEquals(Constants.NOT_FOUND_STATUS_CODE, response.getStatusCode());
        assertEquals("La encuesta buscada, no se encuentra en la db", response.getData().get("message"));
    }

    @Test
    @DisplayName("Prueba que desactiva la encuesta ya que no hay personas asignadas y tampoco tiene respuestas")
    public void testDeactivateSurveyBecauseItHasNoAssignedOrAnswers(){
        preguntaEs.add(preguntaE);
        activeSurvey.setPreguntas(preguntaEs);
        when(surveyRepository.count()).thenReturn(1L);
        when(surveyRepository.existsById(activeSurvey.getIdEncuesta())).thenReturn(true);
        when(surveyRepository.getEncuestaForId(activeSurvey.getIdEncuesta())).thenReturn(activeSurvey);
        when(personaEncuestaRespository.findSurveyPersonByIdSurvey(activeSurvey.getIdEncuesta())).thenReturn(null);

        ServiceResponseDto response = encuestaService.deleteSurvey(activeSurvey.getIdEncuesta());

        assertEquals(Constants.SUCCESS_STATUS_CODE, response.getStatusCode());
        assertEquals("La encuesta se ha desactivado", response.getData().get("message"));
    }

    @Test
    @DisplayName("Prueba que genera codigo 500 al tratar de eliminar encuesta, por error en el servidor")
    public void testErrorWhenTryingToDeleteSurvey(){
        when(surveyRepository.count()).thenReturn(1L);
        when(surveyRepository.existsById(activeSurvey.getIdEncuesta())).thenReturn(true);

        ServiceResponseDto response = encuestaService.deleteSurvey(activeSurvey.getIdEncuesta());

        assertEquals(Constants.INTERNAL_SERVER_ERROR_STATUS_CODE, response.getStatusCode());
    }

    @Test
    @DisplayName("Prueba para verificar el comportamiento cuando no se encuentra la encuesta por su ID")
    public void testGetSurveyWithQuestionsAndAnswersWithCountNotZeroAndSurveyNull(){
        //Simulacion de busqueda en la base de datos (count != 0, existsById == null)
        when(surveyRepository.count()).thenReturn(1L);
        when(surveyRepository.existsById(anyString())).thenReturn(false);
        //Ejecutar el servicio
        ServiceResponseDto response = encuestaService.getSurveyWithQuestionsAndAnswers("Sebas321");
        //Verificar los resultados esperados
        assertEquals(Constants.NOT_FOUND_STATUS_CODE, response.getStatusCode());
        assertEquals("No se encuestra la encuesta buscada!", response.getData().get("message"));
    }

    @Test
    @DisplayName("Prueba para verificar el comportamiento cuando las preguntas tienen respuestas")
    public void testGetSurveyWithQuestionsAndAnswersWithCountNotZeroSurveyNotNullAndAnswersNotNull(){
        //Simulacion de busqueda en la base de datos (count !=0, existsById !=null, isSurveyWithAnswers == true )
        Encuesta survey = activeSurvey;
        preguntaE.setTipoPregunta(tipoPregunta);
        respuestas.add(respuesta);
        preguntaE.setRespuestas(respuestas);
        preguntaEs.add(preguntaE);
        survey.setPreguntas(preguntaEs);
        survey.setTipoEncuesta(tipoEncuesta);
        when(surveyRepository.getEncuestaForId("Sebas321")).thenReturn(survey);
        when(surveyRepository.count()).thenReturn(1L);
        when(surveyRepository.existsById(anyString())).thenReturn(true);
        //Ejecutar el servicio
        ServiceResponseDto response = encuestaService.getSurveyWithQuestionsAndAnswers("Sebas321");
        //Verificar los resultados esperados
        assertEquals(Constants.SUCCESS_STATUS_CODE, response.getStatusCode());
        assertEquals(1 , response.getData().get("total"));
    }
    @Test
    @DisplayName("Prueba para verificar el comportamiento cuando las preguntas no tienen no tienen respuestas")
    public void testGetSurveyWithQuestionsAndAnswersWithCountNotZeroSurveyNotNullAndAnswersNull() {
        //Simulacion de busqueda en la base de datos (count !=0, existsById !=null, isSurveyWithAnswers == false )
        Encuesta survey = activeSurvey;
        preguntaE.setTipoPregunta(tipoPregunta);
        preguntaEs.add(preguntaE);
        survey.setPreguntas(preguntaEs);
        survey.setTipoEncuesta(tipoEncuesta);
        when(surveyRepository.getEncuestaForId("Sebas321")).thenReturn(survey);
        when(surveyRepository.count()).thenReturn(1L);
        when(surveyRepository.existsById(anyString())).thenReturn(true);
        //Ejecutar el servicio
        ServiceResponseDto response = encuestaService.getSurveyWithQuestionsAndAnswers("Sebas321");
        //Verificar los resultados esperados
        assertEquals(Constants.NO_CONTENT_STATUS_CODE, response.getStatusCode());
        assertEquals("La encuesta existe, pero no tiene respuestas asociadas!", response.getData().get("message"));
    }
    @Test
    @DisplayName("Prueba para verificar el comportamiento cuando se produce un error en el repositorio")
    public void testGetSurveyWithQuestionsAndAnswersWithCountError(){
        //Silumacion de busqueda en la base de datos (count == error)
        when(surveyRepository.count()).thenThrow(new RuntimeException("Simulated error"));
        //Ejecutar el servicio
        ServiceResponseDto response = encuestaService.getSurveyWithQuestionsAndAnswers("Sebas321");
        //Verificar los resultados esperados
        assertEquals(Constants.INTERNAL_SERVER_ERROR_STATUS_CODE, response.getStatusCode());
        assertEquals("Error al buscar la encuesta", response.getData().get("message"));
    }

    @Test
    @DisplayName("Prueba para verificar el comportamiento cuando se tiene una encuesta activa sin preguntas(if)")
    public void testListSurveyWithoutQuestions() {
        activeSurvey.setTipoEncuesta(tipoEncuesta);
        activeSurvey.setCantidadPreguntas(0);

        List<Encuesta> surveylist = new ArrayList<>();
        surveylist.add(activeSurvey);
        when(surveyRepository.count()).thenReturn(1L);
        when(surveyRepository.findAll()).thenReturn(surveylist);

        ServiceResponseDto serviceResponseDto = encuestaService.listSurveyWithoutQuestions(1, 1, null);

        EncuestaDto encuestaDto = EncuestaDto.convertSurveyToSurveyDto(activeSurvey);
        List<EncuestaDto> listSurveyDTO = new ArrayList<>();
        listSurveyDTO.add(encuestaDto);
        Page<EncuestaDto> result = encuestaService.convertirListaAConsultaPaginada(listSurveyDTO, 1, 1);

        Assertions.assertEquals(result.getContent().get(0).getDsSurvey(), surveylist.get(0).getDsEncuesta());
        Assertions.assertEquals(result.getSize(),1);
        Assertions.assertEquals(result.getTotalPages(), 1);
        Assertions.assertEquals(result.getTotalElements(), 1);
        Assertions.assertEquals(Constants.SUCCESS_STATUS_CODE,serviceResponseDto.getStatusCode());
    }

    @Test
    @DisplayName("Prueba para verificar el comportamiento cuando se tiene una encuesta activa sin preguntas(else)")
    public void testGetListSurveyWithoutQuestionsError(){
        when(surveyRepository.count()).thenReturn(0L);
        ServiceResponseDto serviceResponseDto = encuestaService.listSurveyWithoutQuestions(1,1,null);
        Assertions.assertEquals(Constants.NOT_FOUND_STATUS_CODE,serviceResponseDto.getStatusCode());
    }
    @Test
    @DisplayName("Prueba para verificar el comportamiento cuando se tiene una encuesta activa sin preguntas(catch)")
    public void testGetListSurveyWithoutQuestionsErrorException(){

        when(surveyRepository.count()).thenThrow(new RuntimeException("Simulated error"));

        ServiceResponseDto serviceResponseDto = encuestaService.listSurveyWithoutQuestions(1,1,null);

        assertEquals(Constants.INTERNAL_SERVER_ERROR_STATUS_CODE, serviceResponseDto.getStatusCode());
        assertEquals("Error al listar las encuestas", serviceResponseDto.getData().get("message"));
    }

    @Test@DisplayName("Prueba para verificar la conversión de una lista EncuestaDTO a una pagina")
    public void testConvertirListaAConsultaPaginada(){
        List<EncuestaDto> lista = new ArrayList<>();
        activeSurvey.setTipoEncuesta(tipoEncuesta);
        EncuestaDto encuestaDto = EncuestaDto.convertSurveyToSurveyDto(activeSurvey);
        lista.add(encuestaDto);
        Page<EncuestaDto> result = encuestaService.convertirListaAConsultaPaginada(lista,1,1);
        Assertions.assertEquals(result.getTotalElements(),1);
        Assertions.assertEquals(result.getTotalPages(),1);
        Assertions.assertEquals(result.get().collect(Collectors.toList()), lista);
    }

}