import { defineStore } from 'pinia';
import { Auth0Client, createAuth0Client, type User } from '@auth0/auth0-spa-js';
import type { RedirectLoginResult } from '@auth0/auth0-spa-js';
import { computed, ref } from 'vue';

export const useAuthenticationStore = defineStore('authentication', () => {
    const auth0 = ref<Auth0Client | undefined>(undefined);
    const user = ref<User | undefined>(undefined);

    const isInitialised = computed(() => auth0.value !== undefined);

    const init = async () => {
        if (isInitialised.value) {
            return;
        }

        auth0.value = await createAuth0Client({
            domain: import.meta.env.VITE_AUTH0_DOMAIN,
            clientId: import.meta.env.VITE_AUTH0_CLIENT_ID,
            authorizationParams: {
                redirect_uri: import.meta.env.VITE_AUTH0_CALLBACK_URL
            },
            useRefreshTokens: true,
            cacheLocation: 'localstorage'
        });

        await updateUser();
    };

    const isAuthenticated = async (): Promise<boolean> => {
        if (auth0.value == null) {
            return false;
        }

        return auth0.value.isAuthenticated();
    };

    const loginWithRedirect = async (): Promise<void> => {
        await auth0.value?.loginWithRedirect();
    };

    const handleRedirectCallback = async (): Promise<RedirectLoginResult | undefined> => {
        const returnValue = await auth0.value?.handleRedirectCallback();
        await updateUser();
        return returnValue;
    };

    const updateUser = async (): Promise<void> => {
        if (await isAuthenticated()) {
            user.value = await getUser();
        } else {
            user.value = undefined;
        }
    };

    const getUser = async (): Promise<User | undefined> => {
        return auth0.value?.getUser();
    };

    const getToken = async (): Promise<String | undefined> => {
        return auth0.value?.getTokenSilently();
    };

    const logout = async (): Promise<void> => {
        return auth0.value?.logout();
    };

    return {
        auth0,
        user,
        init,
        isAuthenticated,
        loginWithRedirect,
        handleRedirectCallback,
        getUser,
        getToken,
        logout,
        isInitialised
    };
});
