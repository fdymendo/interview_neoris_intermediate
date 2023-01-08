package com.fdymendo.interview.neoris;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fdymendo.interview.neoris.controller.ClientesController;
import com.fdymendo.interview.neoris.model.dto.ClienteDTO;
import com.fdymendo.interview.neoris.model.dto.PersonaDTO;
import com.google.gson.Gson;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class NeorisApplicationTests {

  @Autowired
  private ClientesController clientesController;
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private Gson gson;

  @Test
  void createUser() throws Exception {
    ClienteDTO cliente = new ClienteDTO();
    cliente.setContrasenia("1234");
    cliente.setEstado(1);

    PersonaDTO personaDTO = new PersonaDTO();
    personaDTO.setNombre("Jose Lema");
    personaDTO.setGenero("Masculino");
    personaDTO.setEdad(20);
    personaDTO.setDireccion("Otavalo sn y principal");
    personaDTO.setIdentificacion(123465);
    personaDTO.setTelefono("098254785");
    cliente.setPersonaInfo(personaDTO);

    this.mockMvc
        .perform(post("/api/clientes").contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("UTF-8").content(gson.toJson(cliente)))
        .andDo(System.out::println).andExpect(status().isOk());

    assertThat(clientesController).isNotNull();
  }

  @Test
  void createUserBadRequest() throws Exception {
    ClienteDTO cliente = new ClienteDTO();
    cliente.setContrasenia("1234");
    cliente.setEstado(1);

    this.mockMvc
        .perform(post("/api/clientes").contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("UTF-8").content(gson.toJson(cliente)))
        .andDo(System.out::println).andExpect(status().isBadRequest())
        .andExpect(jsonPath("message", is("Se requiere mas informacion")));

    assertThat(clientesController).isNotNull();
  }
}
