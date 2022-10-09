<template>
    <v-simple-table>
        <template v-slot:default>
            <thead>
                <tr>
                    <th class="text-left">
                        Tareas asignadas
                    </th>
                    <th class="text-left">
                        Estado
                    </th>
                    <th class="text-left">
                    </th>
                    <th class="text-left">
                    </th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="tarea in tareas" :key="tarea.id" v-bind:id="'tarea_'+tarea.id" v-if="tarea.id_estado == 0">
                    <td>{{ tarea.nombre }}</td>
                    <td>
                        Pendiente
                    </td>
                    <td>
                        <v-btn depressed color="primary" @click="aceptFunction(tarea.id)" href="http://localhost:3000/get_tasks">
                            Aceptar
                        </v-btn>
                    </td>
                    <td>
                        <v-btn depressed color="error" @click="rejectFunction(tarea.id)" href="http://localhost:3000/get_tasks">
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
            axios.patch("http://localhost:8081/tarea/aceptar/" + id)
            .then(response => {
                console.log(response)
            })
        },
        rejectFunction(id){
            axios.patch("http://localhost:8081/tarea/rechazar/" + id)
            .then(response => {
                console.log(response)
            })
        }
    },
    beforeCreate() {
        axios.get("http://localhost:8081/tarea/voluntario/" + localStorage.getItem('id'))
        .then(response => {
            console.log(localStorage.getItem('id'));
            this.tareas = response.data;
        })
    }
}
</script>
    
    









