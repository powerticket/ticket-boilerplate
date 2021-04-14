FROM node:14 AS build-stage
WORKDIR /app
COPY . .
RUN npm i
RUN npm run build

FROM nginx:alpine AS prod-stage
COPY --from=build-stage /app/dist /usr/share/nginx/html
