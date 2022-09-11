<template>
    <form>
      <v-text-field
        v-model="name"
        :error-messages="nameErrors"
        :counter="10"
        label="Institucion"
        required
       
      ></v-text-field>

      <div id="app">
  <v-app id="inspire">
    <v-form>
      <v-container grid-list-xl>
        <v-layout wrap>
  
          <v-flex xs12 sm6 md3>
            <v-text-field
              type="number" 
              label="ID"  
              step="any"
              min="0"
              ref="input"
              :rules="[numberRule]"
              v-model.number="number"
            ></v-text-field>
          </v-flex>
          
          
  
        </v-layout>
        <v-btn
        class="mr-4"
        @click="submit"
      >
        actualizar
      
      </v-btn>
      </v-container>
    </v-form>
  </v-app>
</div>
  
      
    </form>
  </template>