import type { RouteLocationRaw, RouteLocationNormalized } from 'vue-router';
import { useAuthenticationStore } from '@/store/authentication.store';
import { Routenames } from '@/router/Routenames';

export async function globalNavigationGuard(to: RouteLocationNormalized, from: RouteLocationNormalized): Promise<boolean | RouteLocationRaw> {
    const authStore = useAuthenticationStore();

    await authStore.init();

    if (to.name != Routenames.LOGIN && !(await authStore.isAuthenticated())) {
        return { name: Routenames.LOGIN };
    }

    return true;
}
