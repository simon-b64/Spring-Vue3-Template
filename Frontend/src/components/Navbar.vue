<script setup lang="ts">
import type { MenuItem, MenuItemCommandEvent } from 'primevue/menuitem';
import router from '@/router';
import { Routenames } from '@/router/Routenames';
import { ref } from 'vue';
import { useAuthenticationStore } from '@/store/authentication.store';
import Popover from 'primevue/popover';
import { storeToRefs } from 'pinia';

const authStore = useAuthenticationStore();
const { user } = storeToRefs(authStore);

const menuitems: MenuItem[] = [
    {
        label: 'Home',
        icon: 'pi pi-home',
        routeName: Routenames.HOME,
        command: handleItemClick
    }
];

function handleItemClick(event: MenuItemCommandEvent): void {
    router.push({ name: event.item.routeName });
}

const redirectToLogin = () => {
    router.push({ name: Routenames.LOGIN });
};

const redirectToLogout = () => {
    router.push({ name: Routenames.LOGOUT });
};

const printUser = () => {
    console.log(authStore.user);
};
</script>

<template>
    <Menubar :model="menuitems">
        <template #end>
            <div v-if="user !== undefined" class="endbox">
                <Avatar :icon="user?.picture != null ? undefined : 'pi pi-user'" shape="circle" :image="user?.picture" />
                <h3>{{ user?.name }}</h3>
                <Button label="Logout" @click="redirectToLogout" />
                    <Button label="Print user" @click="printUser" />
            </div>
            <div v-else class="endbox">
                <Button label="Login" @click="redirectToLogin" />
            </div>
        </template>
    </Menubar>
</template>

<style scoped lang="scss">
.endbox {
    display: flex;
    gap: 1rem;
    align-items: center;
}
</style>
