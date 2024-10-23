import { Configuration } from '@/lib/generated/backend-api';
import {useAuthenticationStore} from "@/store/authentication.store";

export const apiConfig = new Configuration({
    basePath: import.meta.env.VITE_API_BASE_URL || 'http://localhost:8081/api',
    credentials: 'omit',
    accessToken: async () => {
        const authStore = useAuthenticationStore();
        return (await authStore.getToken() ?? '') as string;
    },
    fetchApi: fetchWithErrorHandler
});

async function fetchWithErrorHandler(input: RequestInfo | URL, init?: RequestInit): Promise<Response> {
    try {
        const response = await fetch(input, init);
        if (!response.ok) {
            //await handleResponseError(response);
            console.log(response);
        }
        return response;
    } catch (error) {
        //await handleResponseError(error);
        console.log(error);
        throw error;
    }
}

