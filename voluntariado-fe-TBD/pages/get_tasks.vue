<template>
    <v-simple-table>
        <template v-slot:default>
            <thead>
                <tr>
                    <th class="text-left">
                        Tareas asignadas
                    </th>
                    <th class="text-left">
                    </th>
                    <th class="text-left">
                    </th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="tarea in tareas" :key="tarea.id" v-bind:id="'tarea_'+tarea.id">
                    <td>{{ tarea.nombre }}
                    </td>
                    <td>
                        <v-btn depressed color="primary" @click="aceptFunction(tarea.id)">
                            Aceptar
                        </v-btn>
                    </td>
                    <td>
                        <v-btn depressed color="error" @click="rejectFunction(tarea.id)">
                            Rechazar
                        </v-btn>
                    </td>
                </tr>
            </tbody>
        </template>
    </v-simple-table>
</template>

<script>
import axios from "axios";
export default {
    name : 'GetTasksByVolunteerPage',
    data: () => ({
    voluntario: '',
    tareas: [],
    id: ''
    }),
    methods: {
        aceptFunction(id){
            axios.put("http://localhost:8081/tareas/" + id, {
                estado: "Aceptada"
            })
            .then(response => {
                console.log(response)
            })
        },
        rejectFunction(id){
            axios.put("http://localhost:8081/tareas/" + id, {
                estado: "Rechazada"
            })
            .then(response => {
                console.log(response)
            })
        }
    },
    beforeCreate() {
        axios.get("http://localhost:8081/voluntario/name/" + localStorage.getItem('name'))
        .then(response => {
            this.voluntario = response.data;
            this.id = this.voluntario.id;
            console.log(this.voluntario);

        })   
    },
    created() {
        console.log(this.id, "id");
    }
}
</script>
    
    









