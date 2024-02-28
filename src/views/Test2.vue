<template>
<div>
    <h1>Help</h1>

    <el-calendar>
        <template #dateCell="{ data }">
            <p :class="data.isSelected ? 'is-selected' : ''">
                {{ data.day.split('-').slice(1).join('-') }}
                {{ data.isSelected ? '✔️' : '' }}
            </p>
        </template>
    </el-calendar>


</div>
</template>

<script>


    export default {
        name: "Test2",
        components:{

        }
    }
</script>

<style scoped>

</style>
