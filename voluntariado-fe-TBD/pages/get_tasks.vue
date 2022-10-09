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
                <tr v-for="tarea in tareas" :key="tarea.id_tarea" v-bind:id="'tarea_'+tarea.id_tarea">
                    <td>{{ tarea.tarea }}</td>
                    <td>
                        <v-btn depressed color="primary" @click="aceptFunction(tarea.id_tarea)">
                            Aceptar
                        </v-btn>
                    </td>
                    <td>
                        <v-btn depressed color="error" @click="rejectFunction(tarea.id_tarea)">
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
        tareas: []
    }),
    methods: {
        getTasksByVolunteer(id){
            axios.get("http://localhost:8081/voluntario/" + id)
            .then(response => {
                this.tareas = response.data;
            })
            .catch(error => {
                console.log(error);
            });
        },
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
    }
}
</script>
    
    









