<script setup lang="ts"></script>

<template>
    <div class="container">
        <slot />
    </div>
</template>

<style scoped lang="scss">
@use '../assets/breakpoints';

.container {
    margin: auto;

    @media (min-width: breakpoints.$xsmall) {
        max-width: 100%;
    }

    @media (min-width: breakpoints.$small) {
        max-width: 90%;
    }

    @media (min-width: breakpoints.$medium) {
        max-width: 75%;
    }

    @media (min-width: breakpoints.$large) {
        max-width: 60%;
    }
}
</style>
